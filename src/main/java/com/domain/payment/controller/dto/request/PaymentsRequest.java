package com.domain.payment.controller.dto.request;

import com.domain.payment.enums.PaymentsType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PaymentsRequest {

    /**
     * 상품ID
     */
    @NotNull
    private Long productId;

    /**
     * 결제 타입
     */
    @NotBlank
    private PaymentsType type;

    /**
     * 금액
     */
    @NotBlank
    private String amount;

    /**
     * 실패 사유
     */
    private String failMessage;

}
