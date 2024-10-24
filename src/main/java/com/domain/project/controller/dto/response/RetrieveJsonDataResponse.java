package com.domain.project.controller.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class RetrieveJsonDataResponse {

  private final Long projectId;
  private final JsonNode jsonData;

  private RetrieveJsonDataResponse(Long projectId, JsonNode jsonData) {
    this.projectId = projectId;
    this.jsonData = jsonData;
  }

  public static RetrieveJsonDataResponse of(Long projectId, JsonNode jsonData) {
    return new RetrieveJsonDataResponse(projectId, jsonData);
  }
}
