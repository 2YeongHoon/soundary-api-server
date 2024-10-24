package com.domain.user.enums;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 가입유형 상수
 */
@Getter
@AllArgsConstructor
public enum AccountType {
    NORMAL("normal", "일반"),
    FACEBOOK("facebook", "페이스북"),
    GOOGLE("google", "구글"),
    ;
    private final String code;
    private final String title;

    public static Optional<AccountType> codeOf(String code) {
        return Arrays.stream(values())
            .filter(val -> val.getCode().equals(code.toLowerCase()))
            .findFirst();
    }
}
