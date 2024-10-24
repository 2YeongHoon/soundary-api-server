package com.domain.instrument.service;

import com.domain.instrument.controller.dto.response.RetrieveInstrumentResponse;
import com.domain.instrument.service.port.InstrumentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public List<RetrieveInstrumentResponse> retrieve(List<Long> productIds) {
        return instrumentRepository.findAllByProductIds(productIds).stream()
            .map(RetrieveInstrumentResponse::of).collect(
                Collectors.toList());
    }

}
