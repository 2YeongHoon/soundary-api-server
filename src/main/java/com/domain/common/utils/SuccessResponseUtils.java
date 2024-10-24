package com.domain.common.utils;

import com.domain.common.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

public class SuccessResponseUtils {
  // 단순 성공 응답 (code: 200, message: "success", data: null)
  public static <T> ResponseEntity<SuccessResponse> successResponse() {
    SuccessResponse<T> response = new SuccessResponse<>(null);
    return ResponseEntity.ok(response);
  }

  // 데이터가 포함된 성공 응답 (code: 200, message: "success", data: T)
  public static <T> ResponseEntity<SuccessResponse<T>> successResponse(T data) {
    SuccessResponse<T> response = new SuccessResponse<>(data);
    return ResponseEntity.ok(response);
  }
}
