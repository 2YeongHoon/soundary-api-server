package com.domain.common.exception;

import com.domain.common.enums.Errors;
import org.springframework.http.HttpStatus;

/**
 * 권한체크 예외
 */
public class BaseAuthorityException extends BaseRuntimeException {

    public BaseAuthorityException(Errors errors) {
        super(errors, HttpStatus.FORBIDDEN);
    }

}
