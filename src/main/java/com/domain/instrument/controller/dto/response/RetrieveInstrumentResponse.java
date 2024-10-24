package com.domain.instrument.controller.dto.response;

import com.domain.instrument.domain.Instrument;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class RetrieveInstrumentResponse {

    private final Long instrumentId;
    private final List<AudioResponse> audioResponses;


    private RetrieveInstrumentResponse(Instrument instrument) {
        this.instrumentId = instrument.getId();
        this.audioResponses = instrument.getAudio().stream().map(AudioResponse::from).collect(Collectors.toList());
    }

    public static RetrieveInstrumentResponse of(Instrument instrument) {
        return new RetrieveInstrumentResponse(instrument);
    }
}
