package com.domain.external.mail.builder.port;

import com.domain.external.mail.enums.TemplateType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailTemplate {

    MimeMessage makeMessage(TemplateType type, String subject, String receiverEmail) throws MessagingException;

}
