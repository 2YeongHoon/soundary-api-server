package com.domain.payment.infrastructure;

import com.domain.payment.domain.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsJpaRepository extends JpaRepository<Payments, Long> {

}
