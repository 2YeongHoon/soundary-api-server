package com.domain.payment.domain;

import com.domain.common.entity.RootEntity;
import com.domain.common.enums.YesOrNo;
import com.domain.payment.controller.dto.request.TossPaymentRequest;
import com.domain.payment.enums.PaymentsType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 결제 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payments")
public class Payments extends RootEntity {

    @OneToOne(mappedBy = "payments",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private TossPayments tossPayments;

    @Comment("상품ID")
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Comment("유저ID")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Comment("성공여부")
    @Column(name = "success_yn", nullable = false)
    private YesOrNo successYn = YesOrNo.No;

    @Comment("실패사유")
    @Column(name = "content")
    private String fail_message;

    @Comment("결제 타입")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PaymentsType type;

    @Comment("금액")
    @Column(name = "amount", nullable = false)
    private String amount;

    @Builder
    private Payments(
        Long productId,
        Long userId, YesOrNo successYn, String fail_message, PaymentsType type,
        String amount) {
        this.productId = productId;
        this.userId = userId;
        this.successYn = successYn;
        this.fail_message = fail_message;
        this.type = type;
        this.amount = amount;
    }

    public static Payments ofTossSuccess(TossPaymentRequest request, Long userId) {
        return Payments.builder()
            .amount(request.getAmount())
            .productId(request.getProductId())
            .userId(userId)
            .successYn(YesOrNo.Yes)
            .type(PaymentsType.TOSS)
            .build();
    }

    public static Payments ofTossFail(TossPaymentRequest request, Long userId) {
        return Payments.builder()
            .amount(request.getAmount())
            .productId(request.getProductId())
            .userId(userId)
            .successYn(YesOrNo.No)
            .fail_message(request.getFailMessage())
            .type(PaymentsType.TOSS)
            .build();
    }

    public void addToss(TossPayments tossPayments) {
        this.tossPayments = tossPayments;
    }

}
