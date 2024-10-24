package com.domain.project.controller.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateSocketRequest {

    /**
     * 프로젝트 아이디
     */
    @NotBlank
    private Long projectId;

    /**
     * 소켓포트
     */
    @NotBlank
    private Long port;

}
