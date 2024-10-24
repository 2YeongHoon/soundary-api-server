package com.domain.payment.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 커스텀 토스 결제 프로퍼티
 */
@Getter
@ConfigurationProperties(prefix = "custom.payments.client.toss")
@RequiredArgsConstructor
public class TossPaymentsProperties {

    private final String requestUrl;
    private final String widgetSecretKey;

}
