package com.domain.project.controller.dto.request;

import com.domain.common.enums.Authority;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InviteProjectRequest {

  /**
   * 프로젝트 아이디
   */
  @NotBlank
  private Long projectId;

  /**
   * 초대할 유저 아이디(이메일)
   */
  @NotBlank
  private List<String> receiverEmails;

  /**
   * 부여 할 권한
   */
  @NotBlank
  private Authority authority;
}
