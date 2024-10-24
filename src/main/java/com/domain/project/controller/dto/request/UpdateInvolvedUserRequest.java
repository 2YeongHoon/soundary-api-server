package com.domain.project.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateInvolvedUserRequest {

  /**
   * 프로젝트 아이디
   */
  @NotBlank
  private Long projectId;

  /**
   * 업데이트 할 유저 리스트
   */
  @NotBlank
  private List<InvolvedUserRequest> userList;
}
