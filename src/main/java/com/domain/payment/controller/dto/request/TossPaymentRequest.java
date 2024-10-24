package com.domain.payment.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TossPaymentRequest extends PaymentsRequest {

    /**
     * 주문번호
     */
    @NotBlank
    private String orderId;

    /**
     * 토스 결제타입
     */
    @NotBlank
    private String paymentType;

    /**
     * 토스페이먼츠 키
     */
    @NotBlank
    private String paymentKey;

}
