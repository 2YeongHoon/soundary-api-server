package com.domain.project.controller.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemoveUserRequest {

  private Long projectId;
  private List<RemoveUserProjectRequest> users;
  private String projectName;

}
