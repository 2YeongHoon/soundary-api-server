package com.domain.external.ouath.service.port;

import com.domain.external.ouath.dto.OAuthUserInfo;
import com.domain.user.enums.AccountType;
import java.util.Optional;

public interface OAuth2Client {

    Optional<String> requestAccessToken(AccountType type, String code);

    OAuthUserInfo getUserInfo(AccountType type, String accessToken);

}
