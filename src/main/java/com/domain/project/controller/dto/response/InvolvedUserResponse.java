package com.domain.project.controller.dto.response;

import com.domain.common.enums.Authority;
import com.domain.user.domain.UserProject;
import lombok.Getter;

@Getter
public class InvolvedUserResponse {

  private final Long userProjectId;
  private final String alias;
  private final String loginId;
  private final String imageUrl;
  private final String job;
  private final String genre;
  private final String introduce;
  private final Authority authority;

  private InvolvedUserResponse(UserProject userProject) {
    this.userProjectId = userProject.getId();
    this.alias = userProject.getUser().getAlias();
    this.loginId = userProject.getUser().getLinkedAccount().get().getLoginId();
    this.imageUrl = userProject.getUser().getProfileImageUrl();
    this.job = userProject.getUser().getJob();
    this.genre = userProject.getUser().getGenre();
    this.introduce = userProject.getUser().getIntroduce();
    this.authority = userProject.getAuthority();
//    this.agreePolicyCount = account.getUser().getUserPolicies().size();
//    this.involvingUser = project.getInvolvedUsers().stream()
//        .map(User::getLinkedAccount)
//        .flatMap(Optional::stream)
//        .map(UserInfoResponse::from)
//        .collect(Collectors.toList());
  }

  public static InvolvedUserResponse of(UserProject userProject) {
    return new InvolvedUserResponse(userProject);
  }

}
