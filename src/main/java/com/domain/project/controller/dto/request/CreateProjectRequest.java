package com.domain.project.controller.dto.request;


import com.domain.common.enums.YesOrNo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProjectRequest {

    /**
     * 프로젝트 이름
     */
    @NotBlank
    private String name;

    /**
     * 프로젝트 설명
     */
    @NotBlank
    private String description;

    /**
     * 장르
     */
    private String genre;

    /**
     * 공개여부
     */
    private YesOrNo publicYn;

    /**
     * 이미지 파일
     */
    private String imageFile;
}
