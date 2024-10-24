package com.domain.instrument.infrastructure;

import com.domain.instrument.domain.Instrument;
import com.domain.instrument.domain.QInstrument;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class InstrumentQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QInstrument qInstrument = QInstrument.instrument;


    public List<Instrument> findAllByProjectIds(List<Long> productIds) {
        return queryFactory
            .select(qInstrument)
            .from(qInstrument)
            .where(qInstrument.products.id.in(productIds))
            .fetch();
    }
}
