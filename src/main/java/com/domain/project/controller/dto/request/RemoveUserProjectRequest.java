package com.domain.project.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemoveUserProjectRequest {

  /**
   * 유저 프로젝트 아이디
   */
  @NotBlank
  private Long userProjectId;

  /**
   * 추방 시킬 유저 아이디
   */
  private String loginId;

}
