package com.domain.common.exception;

import com.domain.common.enums.Errors;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 리프레시 토큰 만료 예외
 */
@Getter
public class BaseExpireRefreshTokenException extends BaseRuntimeException {

    public BaseExpireRefreshTokenException(Errors errors) {
        super(errors, HttpStatus.UNAUTHORIZED);
    }
}
