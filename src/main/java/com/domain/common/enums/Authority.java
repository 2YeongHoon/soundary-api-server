package com.domain.common.enums;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

    NONE("NONE", "권한없음"),
    OWNER("OWNER", "소유자"),
    VIEWER("VIEWER", "뷰어"),
    EDITOR("EDITOR", "편집자");

    private final String code;
    private final String title;

    public static Optional<Authority> codeOf(String code) {
        return Arrays.stream(values()).filter(val -> val.getCode().equals(code)).findFirst();
    }
}
