package com.domain.instrument.infrastructure;

import com.domain.instrument.domain.Instrument;
import com.domain.instrument.service.port.InstrumentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InstrumentRepositoryImpl implements InstrumentRepository {

    private final InstrumentQueryRepository instrumentQueryRepository;

    @Override
    public List<Instrument> findAllByProductIds(List<Long> productIds) {
        return instrumentQueryRepository.findAllByProjectIds(productIds);
    }
}
