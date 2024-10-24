package com.domain.user.controller.dto.request;


import com.domain.user.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRequest {

    /**
     * 계정타입
     */
    @NotBlank
    private AccountType accountType;

    /**
     * 닉네임
     */
    private String alias;

    /**
     * 이미지 파일 경로
     */
    private String imageUrl;

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
