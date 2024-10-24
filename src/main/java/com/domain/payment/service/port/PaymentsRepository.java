package com.domain.payment.service.port;

import com.domain.payment.domain.Payments;

public interface PaymentsRepository {

    void save(Payments payments);

}
