package com.domain.user.service;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseBadRequestException;
import com.domain.common.exception.BaseExpireRefreshTokenException;
import com.domain.common.security.jwt.enums.TokenType;
import com.domain.common.security.jwt.utils.JwtTokenUtil;
import com.domain.common.utils.EncryptLinkUtils;
import com.domain.external.mail.builder.MailSender;
import com.domain.external.mail.dto.MailDto;
import com.domain.external.mail.dto.MailParams;
import com.domain.external.mail.enums.TemplateType;
import com.domain.external.ouath.dto.OAuthUserInfo;
import com.domain.external.ouath.service.OAuthService;
import com.domain.external.redis.service.RedisService;
import com.domain.user.controller.dto.request.AuthCodeValidationRequest;
import com.domain.user.controller.dto.request.EmailValidationRequest;
import com.domain.user.controller.dto.request.LoginRequest;
import com.domain.user.controller.dto.request.ResetPasswordRequest;
import com.domain.user.controller.dto.request.SignUpRequest;
import com.domain.user.controller.dto.request.SocialSignUpRequest;
import com.domain.user.controller.dto.request.TokenRefreshRequest;
import com.domain.user.controller.dto.request.UpdateRequest;
import com.domain.user.controller.dto.response.LoginResponse;
import com.domain.user.controller.dto.response.UserInfoResponse;
import com.domain.user.domain.Account;
import com.domain.user.domain.User;
import com.domain.user.enums.AccountType;
import com.domain.user.service.port.MailAuthCodeGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${custom.client.base-url}")
    private String clientBaseUrl;

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final MailSender mailSender;
    private final AccountService accountService;
    private final UserService userService;
    private final MailAuthCodeGenerator mailAuthCodeGenerator;
    private final PasswordEncoder passwordEncoder;
    private final OAuthService oauthService;
    private final RedisService redisService;

    public UserInfoResponse me() {
        return UserInfoResponse.from(
            accountService.getByLoginId(this.getLoginId()));
    }

    public Account getAccount() {
        return accountService.getByLoginId(this.getLoginId());
    }

    public Account getAccountByLoginId(String loginId) {
        return accountService.getByLoginId(loginId);
    }

    public LoginResponse login(LoginRequest request) {
        final String assembleId = assembleId(request.loginId(), AccountType.NORMAL);
        // 사용자 인증
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(assembleId, request.password()));

        LoginResponse response = createToken(assembleId);

        return response;
    }

    public LoginResponse tokenRefresh(TokenRefreshRequest request) {
        final String requestToken = request.refreshToken();

        if(jwtTokenUtil.isTokenExpired(requestToken))
            throw new BaseExpireRefreshTokenException(Errors.EXPIRED_REFRESH_TOKEN);

        final String loginId = jwtTokenUtil.extractUsername(request.refreshToken());

        redisService.validateRefreshTokenThrowIfNotFound(loginId, requestToken);
        return createToken(loginId);
    }

    public LoginResponse socialLogin(SocialSignUpRequest request) {
        // 소셜 로그인 1단계: 클라이언트로부터 전달받은 코드 값으로 userInfo를 조회한다.
        OAuthUserInfo userInfo = oauthService.getUserInfoByOauthCodeThrowIfNotExist(
            request.getType(),
            request.getAuthorizationCode());

        final String assembleId = assembleId(userInfo.getEmail(), request.getType());

        // 소셜 로그인 2단계: 계정이 존재하지 않으면 회원가입을 진행한다.
        if(!accountService.existsByEmail(assembleId)) {
            socialSignUp(userInfo, request.getType());
        }

        // 소셜 로그인 3단계: 토큰을 반환한다.
        return createToken(assembleId);
    }

    public void socialSignUp(OAuthUserInfo userInfo, AccountType type) {
        User user = User.builder()
            .alias(userInfo.getName())
            .build();

        Account account = Account.of(
            user,
            assembleId(userInfo.getEmail(), type),
            passwordEncoder.encode(
                assemblePw(userInfo.getEmail(), type)),
            type
        );

        user.addAccount(account);
        userService.save(user);
    }

    public LoginResponse signUp(SignUpRequest request, AccountType type) {
        final String assembleId = assembleId(request.getEmail(), type);
        // 이메일 존재 여부 확인
        accountService.findByLoginIdAndThrowIfExists(assembleId);

        User user = User.builder()
            .alias(request.getAlias())
            .job(request.getJob())
            .age(request.getAge())
            .genre(request.getGenre())
            .introduce(request.getIntroduce())
            .profileImageUrl(
                updateImageFile(request.getImageFile()))
            .build();

        Account account = Account.of(
            user,
            assembleId(request.getEmail(), type),
            passwordEncoder.encode(request.getPassword()),
            type
        );

        user.addAccount(account);
        userService.save(user);

        return createToken(assembleId);
    }

    @Transactional
    public void update(UpdateRequest request, String loginId) {
        User user = accountService.getByLoginId(loginId).getUser();
        user.update(request);
    }

    public void validateEmail(EmailValidationRequest request) {
        // 이메일 존재 여부 확인
        accountService.findByLoginIdAndThrowIfExists(assembleId(request.email(), AccountType.NORMAL));
        final String authCode = mailAuthCodeGenerator.createCode();

        redisService.saveAuthCode(request.email(), authCode);

        MailParams mailParams = MailParams.builder()
            .code(authCode).build();

        List mailList = Arrays.asList(request.email());
        mailSender.send(
            MailDto.of(
                TemplateType.EMAIL_VALIDATION,
                mailParams,
                "[Soundary] 이메일 주소 인증",
                mailList
                ));
    }

    public void resetPasswordEmail(EmailValidationRequest request) throws Exception {

        // 링크 생성
        // 1. 만료 시간 설정
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(30);

        // 2. 데이터를 Map에 넣기
        Map<String, String> dataToEncrypt = new HashMap<>();
        dataToEncrypt.put("email", request.email());
        dataToEncrypt.put("expireTime", expireTime.format(DateTimeFormatter.ISO_DATE_TIME));
        String link = EncryptLinkUtils.createEncryptedLink(dataToEncrypt);

        // 메일 전송
        MailParams mailParams = MailParams.builder()
            .code("30")
            .link(clientBaseUrl + "reset-password?link=" + link)
            .build();

        List mailList = Arrays.asList(request.email());
        mailSender.send(
            MailDto.of(
                TemplateType.RESET_PASSWORD,
                mailParams,
                "[Soundary] 비밀번호 재설정 이메일 입니다.",
                mailList
            ));
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        Account account = accountService.getByLoginId(request.getLoginId());
        account.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }

    public String getLoginId() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails)principal;
            return userDetails.getUsername();
        } catch (Exception e){
            e.printStackTrace();
            throw new BaseBadRequestException(Errors.UNAUTHORIZED);
        }
    }

    public void validateAuthCode(AuthCodeValidationRequest request) {
        final String authCode = redisService.getAuthCodeThrowIfNotExist(request.email());
        if (!Objects.equals(authCode, request.authCode())) {
            throw new BaseBadRequestException(Errors.EXPIRED_AUTH_CODE);
        }
    }

    // TODO: 이미지 업로드 구현
    private String updateImageFile(MultipartFile file) {
        return null;
    }

    private LoginResponse createToken(String loginId) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginId);
        final String accessToken = jwtTokenUtil.generateToken(userDetails.getUsername(), TokenType.ACCESS);
        final String refreshToken = jwtTokenUtil.generateToken(userDetails.getUsername(), TokenType.REFRESH);

        redisService.saveRefreshToken(loginId, refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }

    private String assemblePw(String loginId, AccountType type) {
        return String.format("%s%s", type.getCode(), loginId);
    }

    private String assembleId(String loginId, AccountType type) {
        return String.format("%s_%s", type.getCode(), loginId);
    }

}
