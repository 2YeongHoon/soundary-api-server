package com.domain.payment.service;

import com.domain.payment.controller.dto.request.TossPaymentRequest;
import com.domain.payment.domain.Payments;
import com.domain.payment.domain.TossPayments;
import com.domain.payment.service.port.PaymentsClient;
import com.domain.product.domain.Products;
import com.domain.product.service.ProductService;
import com.domain.user.domain.Account;
import com.domain.user.domain.UserProduct;
import com.domain.user.service.AccountService;
import com.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentsManagementService {

    private final PaymentsService paymentsService;
    private final ProductService productService;
    private final AuthService authService;
    private final AccountService accountService;
    private final PaymentsClient client;

    public void tossSuccess(TossPaymentRequest request) {
        Account account = accountService.getByLoginId(authService.getLoginId());
        final Long userId = account.getUser().getId();

        Payments payments = Payments.ofTossSuccess(request, userId);
        payments.addToss(TossPayments.of(request, payments));

        // 토스 승인 API 요청
        client.confirmPayment(
            request.getOrderId(),
            request.getPaymentKey(),
            request.getAmount());

        paymentsService.save(payments);

        // 유저 상품 연동
        Products product = productService.getByProductId(request.getProductId());
        UserProduct userProduct = UserProduct.of(account.getUser(), product);
        product.addUserProduct(userProduct);
        productService.save(product);
    }

    public void tossFail(TossPaymentRequest request) {
        Account account = accountService.getByLoginId(authService.getLoginId());
        final Long userId = account.getUser().getId();

        Payments payments = Payments.ofTossFail(request, userId);
        payments.addToss(TossPayments.of(request, payments));

        paymentsService.save(payments);
    }

}
