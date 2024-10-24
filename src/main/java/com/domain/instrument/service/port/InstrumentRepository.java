package com.domain.instrument.service.port;

import com.domain.instrument.domain.Instrument;
import java.util.List;

public interface InstrumentRepository {

    List<Instrument> findAllByProductIds(List<Long> productIds);
}
