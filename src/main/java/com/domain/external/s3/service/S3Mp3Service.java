package com.domain.external.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.domain.external.s3.config.S3Properties;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Mp3Service {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public InputStream downloadInstrumentOgg(Long instrumentId, String oggName) {
        String bucketName =
            s3Properties.getS3().getBucketName() + "instrument/" + instrumentId;
        S3Object s3Object = amazonS3.getObject(
            bucketName, oggName);
        return s3Object.getObjectContent();
    }

}
