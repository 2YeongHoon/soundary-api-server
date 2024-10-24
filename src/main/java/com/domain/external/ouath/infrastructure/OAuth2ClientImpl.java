package com.domain.external.ouath.infrastructure;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseBadRequestException;
import com.domain.external.ouath.dto.OAuthUserInfo;
import com.domain.external.ouath.service.port.OAuth2Client;
import com.domain.user.enums.AccountType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2ClientImpl implements OAuth2Client {

    private final GoogleOAuthClient googleOauthClient;
    private final FacebookOAuthClient facebookOauthClient;

    @Override
    public Optional<String> requestAccessToken(AccountType type, String code) {
        if(AccountType.GOOGLE.equals(type)){
            return googleOauthClient.requestAccessToken(code);
        }

        if(AccountType.FACEBOOK.equals(type)){
            return facebookOauthClient.requestAccessToken(code);
        }

        throw new BaseBadRequestException(Errors.BAD_REQUEST);
    }

    @Override
    public OAuthUserInfo getUserInfo(AccountType type, String accessToken) {
        if(AccountType.GOOGLE.equals(type)){
            return googleOauthClient.getUserInfo(accessToken);
        }

        if(AccountType.FACEBOOK.equals(type)){
            return facebookOauthClient.getUserInfo(accessToken);
        }

        throw new BaseBadRequestException(Errors.BAD_REQUEST);
    }

}
