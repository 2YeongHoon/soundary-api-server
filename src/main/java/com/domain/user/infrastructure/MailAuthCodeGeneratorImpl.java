package com.domain.user.infrastructure;

import com.domain.user.service.port.MailAuthCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class MailAuthCodeGeneratorImpl implements MailAuthCodeGenerator {

    private static final int CODE_RANGE = 6;

    @Override
    public String createCode() {
        return RandomStringUtils.randomNumeric(CODE_RANGE);
    }

}
