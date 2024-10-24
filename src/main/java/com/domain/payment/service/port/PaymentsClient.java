package com.domain.payment.service.port;

public interface PaymentsClient {

    void confirmPayment(String orderId, String paymentKey, String amount);
}
