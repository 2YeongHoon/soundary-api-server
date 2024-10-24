package com.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 오류 정의
 */
@Getter
@AllArgsConstructor
public enum Errors {
    BAD_REQUEST(1001, "잘못된 요청입니다"),
    UNAUTHORIZED(1002, "인증되지 않은 사용자입니다"),
    INVALID_TOKEN(1003, "유효하지 않은 토큰입니다"),
    INVALID_EXTERNAL_TOKEN(1004, "유효하지 않은 외부 토큰입니다"),
    INVALID_AUTH_CODE(1005, "유효하지 않은 인증 코드입니다"),
    EXPIRED_AUTH_CODE(1006, "만료된 인증코드입니다"),
    EXPIRED_REFRESH_TOKEN(1007, "만료된 리프레시 토큰입니다"),
    NOT_OWNER(1008, "OWNER 권한이 없습니다."),
    NOT_EDITABLE_USER(1009, "수정 권한이 없습니다."),
    NO_PERMISSION(1010, "권한이 없습니다."),

    DUPLICATED_EMAIL(3002, "이미 가입된 이메일 주소 입니다 다른 메일 주소를 입력해주세요"),

    NOT_FOUND_ACCOUNT(4001, "존재하지 않는 계정입니다"),

    NOT_FOUND_PROJECT(5001, "존재하지 않는 프로젝트 입니다."),

    NOT_FOUND_PRODUCT(6001, "존재하지 않는 상품 입니다."),

    EXTERNAL_API_ERROR(1, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다"),
    INTERNAL_SERVER_ERROR(9999, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다"),
    ;

    private final int code;
    private final String message;

    public String getCodeMessage() {
        return String.format("%s, %s", this.code, this.message);
    }

}
