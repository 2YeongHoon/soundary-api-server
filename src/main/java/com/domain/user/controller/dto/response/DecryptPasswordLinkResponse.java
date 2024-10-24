package com.domain.user.controller.dto.response;

import lombok.Getter;

@Getter
public class DecryptPasswordLinkResponse {

  private final String email;
  private final String expireTime;

  private DecryptPasswordLinkResponse(String email, String expireTime) {
    this.email = email;
    this.expireTime = expireTime;
  }

  public static DecryptPasswordLinkResponse of(String email, String expireTime) {
    return new DecryptPasswordLinkResponse(email, expireTime);
  }

}
