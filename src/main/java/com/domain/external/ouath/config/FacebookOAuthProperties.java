package com.domain.external.ouath.config;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 커스텀 페이스북 OAuth 프로퍼티
 */
@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.facebook")
@RequiredArgsConstructor
public class FacebookOAuthProperties {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String authorizationGrantType;
    private final List<String> scope;

}
