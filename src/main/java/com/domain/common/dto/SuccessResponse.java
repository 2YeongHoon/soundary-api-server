package com.domain.common.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SuccessResponse<T> {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int status;
  private final int code;
  private T data;

  public SuccessResponse(T data) {
    this.status = 200;
    this.code = 200;
    this.data = data;
  }
}
