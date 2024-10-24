package com.domain.payment.service;

import com.domain.payment.domain.Payments;
import com.domain.payment.service.port.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsRepository repository;

    @Transactional
    public void save(Payments payments) {
        repository.save(payments);
    }

}
