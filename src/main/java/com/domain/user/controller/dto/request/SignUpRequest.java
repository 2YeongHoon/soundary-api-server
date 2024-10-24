package com.domain.user.controller.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    /**
     * 이메일
     */
    @NotBlank
    @Email
    private String email;

    /**
     * 패스워드
     */
    @NotBlank
    private String password;

    /**
     * 닉네임
     */
    private String alias;

    /**
     * 이미지 파일
     */
    private MultipartFile imageFile;

    /**
     * 직업
     */
    private String job;

    /**
     * 나이
     */
    private String age;

    /**
     * 선호장르
     */
    private String genre;

    /**
     * 자기소개
     */
    private String introduce;

}
