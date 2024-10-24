package com.domain.common.exception;

import com.domain.common.enums.Errors;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 데이터 없음 예외
 */
@Getter
public class BaseNotFoundException extends BaseRuntimeException {

    public BaseNotFoundException(Errors errors) {
        super(errors, HttpStatus.NOT_FOUND);
    }

}
