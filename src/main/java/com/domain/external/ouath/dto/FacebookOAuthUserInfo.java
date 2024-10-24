package com.domain.external.ouath.dto;

import com.domain.user.enums.AccountType;
import java.util.Map;

public class FacebookOAuthUserInfo implements OAuthUserInfo {

    private Map<String, Object> attributes;

    public FacebookOAuthUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public AccountType getProvider() {
        return AccountType.FACEBOOK;
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
