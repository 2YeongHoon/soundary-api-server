package com.domain.common.exception.handler;

import com.domain.common.dto.ErrorResponse;
import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseRuntimeException;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 전역 예외 핸들러
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("권한 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(HttpStatus.FORBIDDEN, Errors.NO_PERMISSION);
    }

    /**
     * 잘못된 인자 예외
     *
     * @param response HTTP 서블릿 응답객체
     * @param e        예외
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> BadRequestExceptionHandler(HttpServletResponse response,
        BindException e) {
        log.error("잘못된 인자 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST, Errors.BAD_REQUEST);
    }

    /**
     * 인증 예외
     *
     * @param response HTTP 서블릿 응답객체
     * @param e        예외
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> UnauthorizedExceptionHandler(HttpServletResponse response,
        AuthenticationException e) {
        log.error("인증 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(HttpStatus.UNAUTHORIZED, Errors.UNAUTHORIZED);
    }

    /**
     * 런타임 예외는 {@link Errors}에 정의된 예외 코드와 메시지로 처리한다.
     *
     * @param response HTTP 서블릿 응답객체
     * @param e        예외
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ErrorResponse> BaseRunTimeExceptionHandler(HttpServletResponse response,
        BaseRuntimeException e) {
        log.error("런타임 오류가 발생했습니다.", e);
        return ErrorResponse.toResponseEntity(e);
    }

    /**
     * 미확인 예외
     *
     * @param response HTTP 서블릿 응답데이터
     * @param t        최상위 예외
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> ThrowableHandler(HttpServletResponse response,
        Throwable t) throws ParseException {

        t.printStackTrace();
        return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
            Errors.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {
        BeanInstantiationException.class,
        MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponse> beanInstantiationExceptionHandler(Throwable t) {
        log.error(t.getCause().getMessage(), t);
        return ErrorResponse.toResponseEntity(
            HttpStatus.BAD_REQUEST,
            Errors.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(BaseRuntimeException e) {
        log.error(e.getErrorMessage(), e);

        return ErrorResponse.toResponseEntity(e);
    }
}
