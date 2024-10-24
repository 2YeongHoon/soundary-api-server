package com.domain.project.controller.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateJsonRequest {

  /**
   * 프로젝트 아이디
   */
  @NotBlank
  private Long projectId;

  /**
   * json data
   */
  @NotBlank
  private JsonNode jsonData;
}
