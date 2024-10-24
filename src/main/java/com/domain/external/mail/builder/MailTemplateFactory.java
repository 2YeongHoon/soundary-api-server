package com.domain.external.mail.builder;

import com.domain.external.mail.builder.port.MailTemplate;
import com.domain.external.mail.dto.MailDto;
import com.domain.external.mail.infrastructure.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Component
public class MailTemplateFactory implements MailSender {

    private final SpringTemplateEngine engine;
    private final JavaMailSender sender;

    @Autowired
    private MailTemplateFactory(SpringTemplateEngine engine, JavaMailSender sender) {
        this.engine = engine;
        this.sender = sender;
    }

    @Override
    public void send(MailDto dto) {
        try {
            for (String receiverEmail : dto.getReceivers()) {
                MimeMessage message = messageBuild(dto).makeMessage(dto.getType(), dto.getSubject(), receiverEmail);
                sender.send(message);
            }
        } catch (MessagingException e) {
            log.error(e.toString());
        }
    }

    private MailTemplate messageBuild(MailDto dto) throws MessagingException {
        return new EmailTemplate(sender, engine, dto);
    }

}
