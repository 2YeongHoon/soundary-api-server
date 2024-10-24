package com.domain.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptUtils {
  private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final String SECRET_KEY = "my_secret_key_123456789012345677"; // 32-byte secret key
  private static final String INIT_VECTOR = "RandomKey9926712"; // 16-byte IV

  public static String encrypt(String value) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
    SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

    byte[] encrypted = cipher.doFinal(value.getBytes());
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public static String decrypt(String encrypted) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
    SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

    byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

    return new String(original);
  }
}
