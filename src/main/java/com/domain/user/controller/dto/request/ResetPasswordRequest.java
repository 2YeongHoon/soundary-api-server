package com.domain.user.controller.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResetPasswordRequest {

    /**
     * 로그인 아이디
     */
    @NotBlank
    private String loginId;

    /**
     * 새로운 비밀번호
     */
    @NotBlank
    private String newPassword;

}
