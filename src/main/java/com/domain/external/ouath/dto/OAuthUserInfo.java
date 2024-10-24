package com.domain.external.ouath.dto;

import com.domain.user.enums.AccountType;

public interface OAuthUserInfo {
    String getProviderId();
    AccountType getProvider();
    String getEmail();
    String getName();
}
