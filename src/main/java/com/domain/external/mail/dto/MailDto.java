package com.domain.external.mail.dto;

import com.domain.external.mail.enums.TemplateType;
import java.util.List;
import lombok.Getter;

@Getter
public class MailDto {

    public final TemplateType type;
    public final MailParams params;
    public final String subject;
    public final List<String> receivers;

    private MailDto(TemplateType type, MailParams params, String subject, List<String> receivers) {
        this.type = type;
        this.params = params;
        this.subject = subject;
        this.receivers = receivers;
    }

    public static MailDto of(TemplateType type, MailParams params, String subject, List<String> receivers) {
        return new MailDto(type, params, subject, receivers);
    }

}
