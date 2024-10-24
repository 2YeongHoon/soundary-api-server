package com.domain.project.controller.dto.response;

import com.domain.common.enums.Authority;
import lombok.Getter;

@Getter
public class DecryptLinkResponse {

  private final Long projectId;
  private final String expireTime;
  private final Authority authority;

  private DecryptLinkResponse(Long projectId, String expireTime, Authority authority) {
    this.projectId = projectId;
    this.expireTime = expireTime;
    this.authority = authority;
  }

  public static DecryptLinkResponse of(Long projectId, String expireTime, Authority authority) {
    return new DecryptLinkResponse(projectId, expireTime, authority);
  }

}
