package com.domain.external.mail.dto;

import com.domain.external.mail.enums.TemplateType;
import lombok.Getter;

@Getter
public class ProjectInviteDto {

  public final TemplateType type;
  public final String receiverEmail;
  public final String senderEmail;
  public final String senderName;
  public final String projectName;
  public final String link;

  private ProjectInviteDto(TemplateType type, String receiverEmail,
      String senderEmail, String senderName, String projectName, String link) {
    this.type = type;
    this.receiverEmail = receiverEmail;
    this.senderEmail = senderEmail;
    this.senderName = senderName;
    this.projectName = projectName;
    this.link = link;
  }

  public static ProjectInviteDto of(TemplateType type, String receiverEmail,
      String senderEmail, String senderName, String projectName, String link) {
    return new ProjectInviteDto(type, receiverEmail,
        senderEmail, senderName, projectName, link);
  }

}
