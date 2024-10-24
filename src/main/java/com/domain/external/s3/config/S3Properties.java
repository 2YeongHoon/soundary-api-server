package com.domain.external.s3.config;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "spring.cloud.aws.s3properties")
@RequiredArgsConstructor
public class S3Properties {

  private final Credentials credentials;
  private final S3 s3;
  private final String region;

  @Data
  public static class Credentials {
    private String accessKey;
    private String secretKey;
  }

  @Data
  public static class S3 {
    private String bucketName;
  }
}
