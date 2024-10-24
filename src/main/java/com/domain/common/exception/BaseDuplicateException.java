package com.domain.common.exception;

import com.domain.common.enums.Errors;
import org.springframework.http.HttpStatus;

/**
 * 데이터 중복 예외
 */
public class BaseDuplicateException extends BaseRuntimeException {

    public BaseDuplicateException(Errors errors) {
        super(errors, HttpStatus.CONFLICT);
    }

}
