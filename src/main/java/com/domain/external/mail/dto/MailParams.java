package com.domain.external.mail.dto;

import com.domain.external.mail.enums.TemplateType;
import java.util.List;
import lombok.Builder;

@Builder
public class MailParams {

  public final TemplateType type;
  public final String code;
  public final String senderEmail;
  public final String senderName;
  public final String projectName;
  public final String link;
}
