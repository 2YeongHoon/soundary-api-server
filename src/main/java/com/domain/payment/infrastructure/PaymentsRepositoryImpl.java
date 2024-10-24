package com.domain.payment.infrastructure;

import com.domain.payment.domain.Payments;
import com.domain.payment.service.port.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentsRepositoryImpl implements PaymentsRepository {

    private final PaymentsJpaRepository repository;

    @Override
    public void save(Payments payments) {
        repository.save(payments);
    }
}
