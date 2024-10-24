package com.domain.payment.infrastructure;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseRuntimeException;
import com.domain.payment.config.TossPaymentsProperties;
import com.domain.payment.service.port.PaymentsClient;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TossPaymentClient implements PaymentsClient {

    private final RestTemplate restTemplate;
    private final TossPaymentsProperties properties;

    @Autowired
    public TossPaymentClient(RestTemplateBuilder builder, TossPaymentsProperties properties) {
        this.restTemplate = builder.build();
        this.properties = properties;
    }

    @Override
    public void confirmPayment(String orderId, String paymentKey, String amount) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + getEncodedSecretKey());
            headers.set("Content-Type", "application/json");

            Map<String, Object> body = new HashMap<>();
            body.put("paymentKey", paymentKey);
            body.put("orderId", orderId);
            body.put("amount", amount);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            // API 호출
            ResponseEntity<String> response = restTemplate.exchange(
                properties.getRequestUrl(),
                HttpMethod.POST,
                entity,
                String.class
            );
        }catch (Exception e){
            log.error(e.toString());
            throw new BaseRuntimeException(Errors.EXTERNAL_API_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private String getEncodedSecretKey() {
        return Base64.getEncoder().encodeToString((properties.getWidgetSecretKey() + ":").getBytes());
    }

}

