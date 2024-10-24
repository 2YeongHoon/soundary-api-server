package com.domain.user.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.AesEncryptUtils;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.user.controller.dto.request.AuthCodeValidationRequest;
import com.domain.user.controller.dto.request.EmailValidationRequest;
import com.domain.user.controller.dto.request.LoginRequest;
import com.domain.user.controller.dto.request.ResetPasswordRequest;
import com.domain.user.controller.dto.request.SignUpRequest;
import com.domain.user.controller.dto.request.SocialSignUpRequest;
import com.domain.user.controller.dto.request.TokenRefreshRequest;
import com.domain.user.controller.dto.response.DecryptPasswordLinkResponse;
import com.domain.user.controller.dto.response.LoginResponse;
import com.domain.user.enums.AccountType;
import com.domain.user.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "/signup")
    public ResponseEntity<SuccessResponse<LoginResponse>> normalSignup(@RequestBody @Valid SignUpRequest request) {
        return SuccessResponseUtils.successResponse(authService.signUp(request, AccountType.NORMAL));
    }

    @PostMapping(value = "/signup/check/email")
    public ResponseEntity<SuccessResponse> validateEmail(@RequestBody @Valid EmailValidationRequest request) {
        authService.validateEmail(request);
        return SuccessResponseUtils.successResponse();
    }

    @PostMapping(value = "/signup/check/auth-code")
    public ResponseEntity<SuccessResponse> validateAuthCode(@RequestBody @Valid AuthCodeValidationRequest request) {
        authService.validateAuthCode(request);
        return SuccessResponseUtils.successResponse();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<SuccessResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        return SuccessResponseUtils.successResponse(authService.login(request));
    }

    @PutMapping(value = "/login/social")
    public ResponseEntity<SuccessResponse<LoginResponse>> socialLogin(@RequestBody @Valid SocialSignUpRequest request) {
        return SuccessResponseUtils.successResponse(authService.socialLogin(request));
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<SuccessResponse<LoginResponse>> tokenRefresh(
        @RequestBody @Valid TokenRefreshRequest request) {
        return SuccessResponseUtils.successResponse(authService.tokenRefresh(request));
    }

    @PostMapping(value = "/reset/password/email")
    public ResponseEntity<SuccessResponse> resetPasswordEmail(@RequestBody @Valid EmailValidationRequest request)
        throws Exception {
        authService.resetPasswordEmail(request);
        return SuccessResponseUtils.successResponse();
    }

    @PostMapping(value = "/reset-password")
    public ResponseEntity<SuccessResponse> resetPassword(@RequestBody @Valid ResetPasswordRequest request)
        throws Exception {
        authService.resetPassword(request);
        return SuccessResponseUtils.successResponse();
    }

    @GetMapping("/decrypt-pwd-link")
    public ResponseEntity<SuccessResponse<DecryptPasswordLinkResponse>> decryptLink(@RequestParam String linkKey) throws Exception {
        String decryptedData = AesEncryptUtils.decrypt(linkKey);

        DecryptPasswordLinkResponse response = objectMapper.readValue(decryptedData, DecryptPasswordLinkResponse.class);
        return SuccessResponseUtils.successResponse(response);
    }
}
