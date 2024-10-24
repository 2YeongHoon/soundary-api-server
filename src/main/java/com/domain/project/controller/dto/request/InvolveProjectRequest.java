package com.domain.project.controller.dto.request;

import com.domain.common.enums.Authority;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvolveProjectRequest {

  /**
   * 프로젝트 아이디
   */
  @NotBlank
  private Long projectId;

  /**
   * 프로젝트 참여 할 유저 아이디
   */
  @NotBlank
  private String loginId;

  /**
   * 프로젝트 참여 할 유저 권한
   */
  @NotBlank
  private Authority authority;
}
