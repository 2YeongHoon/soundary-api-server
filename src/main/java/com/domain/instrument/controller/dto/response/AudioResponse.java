package com.domain.instrument.controller.dto.response;

import com.domain.instrument.domain.Audio;
import lombok.Getter;

@Getter
public class AudioResponse {

    private final Long audioId;
    private final String s3Key;
    private final Long code;
    private final String description;

    private AudioResponse(Audio audio) {
        this.audioId = audio.getId();
        this.s3Key = audio.getS3Key();
        this.code = audio.getCode();
        this.description = audio.getDescription();
    }

    public static AudioResponse from(Audio audio) {
        return new AudioResponse(audio);
    }
}
