package com.domain.external.mail.builder;

import com.domain.external.mail.dto.MailDto;

public interface MailSender {

    void send(MailDto dto);

}
