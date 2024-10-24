package com.domain.external.ouath.dto;

import com.domain.user.enums.AccountType;
import java.util.Map;

public class GoogleOAuthUserInfo implements OAuthUserInfo {

    private Map<String, Object> attributes;

    public GoogleOAuthUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public AccountType getProvider() {
        return AccountType.GOOGLE;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

}
