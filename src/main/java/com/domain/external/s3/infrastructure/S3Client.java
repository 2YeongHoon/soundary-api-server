package com.domain.external.s3.infrastructure;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.domain.external.s3.config.S3Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class S3Client {

  private final S3Properties s3Properties;

  @Bean
  public AmazonS3 amazonS3() {
    AWSCredentials credentials = new BasicAWSCredentials(s3Properties.getCredentials().getAccessKey(), s3Properties.getCredentials().getSecretKey());

    return AmazonS3ClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(s3Properties.getRegion())
        .build();
  }

  public S3Client(S3Properties s3Properties) {
    this.s3Properties = s3Properties;
  }
}
