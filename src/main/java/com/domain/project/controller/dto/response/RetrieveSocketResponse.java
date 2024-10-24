package com.domain.project.controller.dto.response;

import com.domain.project.domain.ProjectSocket;
import lombok.Getter;

@Getter
public class RetrieveSocketResponse {

  private final Long projectId;
  private final Long port;

  private RetrieveSocketResponse(Long projectId, Long port) {
    this.projectId = projectId;
    this.port = port;
  }

  public static RetrieveSocketResponse of(Long projectId, Long port) {
    return new RetrieveSocketResponse(projectId, port);
  }

  public static RetrieveSocketResponse from(ProjectSocket projectSocket) {
    return new RetrieveSocketResponse(projectSocket.getProject().getId(), projectSocket.getPort());
  }

}
