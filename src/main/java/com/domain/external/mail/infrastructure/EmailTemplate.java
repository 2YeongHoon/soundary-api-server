package com.domain.external.mail.infrastructure;

import com.domain.external.mail.builder.port.MailTemplate;
import com.domain.external.mail.dto.MailDto;
import com.domain.external.mail.enums.TemplateType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

public class EmailTemplate implements MailTemplate {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final MailDto dto;

    public EmailTemplate(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, MailDto dto) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.dto = dto;
    }

    @Override
    public MimeMessage makeMessage(TemplateType type, String subject, String receiverEmail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        helper.setTo(receiverEmail);
        helper.setSubject(subject);
        helper.setText(context(receiverEmail), true);

        return message;
    }

    private String context(String receiverEmail) {
        Context context = new Context();
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("params", dto.getParams());
        map.put("receiverEmail", receiverEmail);
        context.setVariables(map);
        return templateEngine.process(dto.getType().fileName(), context);
    }
}
