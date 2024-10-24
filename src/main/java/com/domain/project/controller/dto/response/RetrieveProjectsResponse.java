package com.domain.project.controller.dto.response;

import com.domain.common.enums.Authority;
import com.domain.common.enums.YesOrNo;
import com.domain.project.domain.Project;
import com.domain.user.domain.Account;
import com.domain.user.domain.User;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class RetrieveProjectsResponse {

  private final Long projectId;
  private final String ownName;
  private final String projectName;
  private final String imageUrl;
  private final String genre;
  private final LocalDate createDate;
  private final String description;
  private final YesOrNo publicYn;
  private final Authority authority;
//  private final List<UserInfoResponse> involvingUser;

  private RetrieveProjectsResponse(Project project, Account account) {
    this.projectId = project.getId();
    this.ownName = project.getOwnUser().map(User::getAlias).orElse(null);
    this.projectName = project.getName();
    this.imageUrl = project.getImageUrl();
    this.genre = project.getGenre();
    this.createDate = project.getCreateDt().toLocalDate();
    this.description = project.getDescription();
    this.publicYn = project.getPublicYn();
    this.authority = project.getAuthority(account);
//    this.involvingUser = project.getInvolvedUsers().stream()
//        .map(User::getLinkedAccount)
//        .flatMap(Optional::stream)
//        .map(UserInfoResponse::from)
//        .collect(Collectors.toList());
  }

  public static RetrieveProjectsResponse of(Project project, Account account) {
    return new RetrieveProjectsResponse(project, account);
  }

}
