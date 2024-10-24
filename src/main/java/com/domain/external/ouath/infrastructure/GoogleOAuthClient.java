package com.domain.external.ouath.infrastructure;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseRuntimeException;
import com.domain.external.ouath.dto.GoogleOAuthUserInfo;
import com.domain.external.ouath.dto.OAuthUserInfo;
import com.domain.external.ouath.config.GoogleOAuthProperties;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleOAuthClient {

    private final RestTemplate restTemplate;
    private final GoogleOAuthProperties properties;
    // TODO: 전역 상수에서 프로퍼티로 변경하기
    private final String tokenUrl = "https://oauth2.googleapis.com/token";
    private final String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

    @Autowired
    public GoogleOAuthClient(RestTemplateBuilder builder, GoogleOAuthProperties properties) {
        this.restTemplate = builder.build();
        this.properties = properties;
    }

    public Optional<String> requestAccessToken(String code) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("client_id", properties.getClientId());
            params.add("client_secret", properties.getClientSecret());
            params.add("code", code);
            params.add("redirect_uri", properties.getRedirectUri());
            params.add("grant_type", properties.getAuthorizationGrantType());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);
            Map<String, Object> responseBody = response.getBody();
            return Optional.ofNullable((String) responseBody.get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public OAuthUserInfo getUserInfo(String accessToken) {
        try{
            // Access Token을 이용해 사용자 정보 요청
            HttpHeaders userInfoHeaders = new HttpHeaders();
            userInfoHeaders.setBearerAuth(accessToken);
            HttpEntity<String> userInfoRequest = new HttpEntity<>(userInfoHeaders);

            ResponseEntity<Map> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequest,
                Map.class);

            return new GoogleOAuthUserInfo(userInfoResponse.getBody());
        }catch (Exception e) {
            e.printStackTrace();
            throw new BaseRuntimeException(Errors.EXTERNAL_API_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
