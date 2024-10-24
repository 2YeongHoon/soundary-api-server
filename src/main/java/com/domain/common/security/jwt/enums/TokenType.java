package com.domain.common.security.jwt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 토큰 타입
 */
@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS, REFRESH;
}
