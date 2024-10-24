package com.domain.project.controller.dto.response;

import lombok.Getter;

@Getter
public class ProjectJsonDataResponse {

  private final Long proejectId;
  private final String jsonData;

  private ProjectJsonDataResponse(Long proejectId, String jsonData) {
    this.proejectId = proejectId;
    this.jsonData = jsonData;
  }

  public static ProjectJsonDataResponse of(Long proejectId, String jsonData) {
    return new ProjectJsonDataResponse(proejectId, jsonData);
  }

}
