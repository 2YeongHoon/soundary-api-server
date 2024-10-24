package com.domain.project.controller.dto.request;

import com.domain.common.enums.Authority;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvolvedUserRequest {

  /**
   * 유저 프로젝트 아이디
   */
  @NotBlank
  private Long id;

  /**
   * 권한
   */
  @NotBlank
  private Authority authority;
}
