package com.domain.common.exception;

import com.domain.common.enums.Errors;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 잘못된 요청 예외
 */
@Getter
public class BaseBadRequestException extends BaseRuntimeException {

    public BaseBadRequestException(Errors errors) {
        super(errors, HttpStatus.BAD_REQUEST);
    }
}
