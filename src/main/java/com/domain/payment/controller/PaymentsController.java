package com.domain.payment.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.payment.controller.dto.request.TossPaymentRequest;
import com.domain.payment.service.PaymentsManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/payments")
@RestController
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentsManagementService paymentsManagementService;

    @PostMapping(value = "/toss/success")
    public ResponseEntity<SuccessResponse> tossSuccess(@RequestBody TossPaymentRequest request) {
        paymentsManagementService.tossSuccess(request);
        return SuccessResponseUtils.successResponse();
    }

    @PostMapping(value = "/toss/fail")
    public ResponseEntity<SuccessResponse> tossFail(@RequestBody TossPaymentRequest request) {
        paymentsManagementService.tossFail(request);
        return SuccessResponseUtils.successResponse();
    }

}
