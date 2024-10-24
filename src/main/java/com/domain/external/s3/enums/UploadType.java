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
public enum UploadType {
    PROFILE("profile", "프로필"),
    PROJECT("project", "프로젝트"),
    ;
    private final String code;
    private final String title;

    public static Optional<UploadType> codeOf(String code) {
        return Arrays.stream(values())
            .filter(val -> val.getCode().equals(code.toLowerCase()))
            .findFirst();
    }
}
