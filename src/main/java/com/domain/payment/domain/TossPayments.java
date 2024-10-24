package com.domain.payment.domain;

import com.domain.common.entity.ChildEntity;
import com.domain.payment.controller.dto.request.TossPaymentRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 토스 결제 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "toss_payments")
public class TossPayments extends ChildEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payments_id", referencedColumnName = "id")
    private Payments payments;

    @Comment("주문번호")
    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Comment("토스 결제 타입")
    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Comment("결제 키")
    @Column(name = "payment_key", nullable = false)
    private String paymentKey;

    @Builder
    private TossPayments(Payments payments, String orderId, String paymentType, String paymentKey) {
        this.payments = payments;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.paymentKey = paymentKey;
    }

    public static TossPayments of(TossPaymentRequest request, Payments payments) {
        return TossPayments.builder()
            .payments(payments)
            .orderId(request.getOrderId())
            .paymentKey(request.getPaymentKey())
            .paymentType(request.getPaymentType())
            .build();
    }

}
