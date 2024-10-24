package com.domain.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncryptLinkUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String createEncryptedLink(Map<String,String> body) throws Exception {

    // 1. 데이터를 JSON으로 직렬화
    String jsonData = objectMapper.writeValueAsString(body);

    // 2. JSON 데이터를 AES로 암호화
    String encryptedData = AesEncryptUtils.encrypt(jsonData);

    // 3. 링크 param 생성
    return URLEncoder.encode(encryptedData, StandardCharsets.UTF_8.toString());
  }
}
