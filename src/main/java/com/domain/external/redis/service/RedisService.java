package com.domain.external.redis.service;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseExpireRefreshTokenException;
import com.domain.common.exception.BaseNotFoundException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    private static final String REFRESH_TOKEN_NAME_SPACE = "refresh_token";
    private static final String AUTH_CODE_NAME_SPACE = "auth_code";
    private static final int REFRESH_TOKEN_TTL = 5;
    private static final int AUTH_CODE_TTL = 5;

    public void saveAuthCode(String loginId, String code) {
        redisTemplate.opsForValue().set(
            addPrefix(AUTH_CODE_NAME_SPACE, loginId),
            code,
            AUTH_CODE_TTL,
            TimeUnit.MINUTES);
    }

    public void saveRefreshToken(String loginId, String token) {
        redisTemplate.opsForValue().set(
            addPrefix(REFRESH_TOKEN_NAME_SPACE, loginId),
            token,
            REFRESH_TOKEN_TTL,
            TimeUnit.MINUTES);
    }

    public String getAuthCodeThrowIfNotExist(String loginId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(addPrefix(AUTH_CODE_NAME_SPACE, loginId)))
            .orElseThrow(() -> new BaseNotFoundException(Errors.EXPIRED_AUTH_CODE));
    }

    public void validateRefreshTokenThrowIfNotFound(String loginId, String requestToken) {
        final String oldToken = redisTemplate.opsForValue().get(addPrefix(REFRESH_TOKEN_NAME_SPACE, loginId));
        if (!requestToken.equals(oldToken)) {
            throw new BaseExpireRefreshTokenException(Errors.INVALID_TOKEN);
        }
    }

    private String addPrefix(String prefix, String key) {
        return String.format("%s:%s", prefix, key);
    }

}
