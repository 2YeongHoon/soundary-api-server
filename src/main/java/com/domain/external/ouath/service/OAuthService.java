package com.domain.external.ouath.service;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseRuntimeException;
import com.domain.external.ouath.dto.OAuthUserInfo;
import com.domain.external.ouath.service.port.OAuth2Client;
import com.domain.user.enums.AccountType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuth2Client client;

    public OAuthUserInfo getUserInfoByOauthCodeThrowIfNotExist(AccountType type, String code) {
        final String accessToken = getAccessToken(type, code).orElseThrow
            (() ->new BaseRuntimeException(Errors.EXTERNAL_API_ERROR, HttpStatus.SERVICE_UNAVAILABLE));
        return getUserInfo(type, accessToken);
    }

    private Optional<String> getAccessToken(AccountType type, String code) {
        return client.requestAccessToken(type, code);
    }

    private OAuthUserInfo getUserInfo(AccountType type, String accessToken) {
        return client.getUserInfo(type, accessToken);
    }

}
