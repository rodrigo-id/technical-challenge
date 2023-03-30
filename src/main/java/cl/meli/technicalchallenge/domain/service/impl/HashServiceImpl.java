package cl.meli.technicalchallenge.domain.service.impl;

import cl.meli.technicalchallenge.domain.service.HashService;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashServiceImpl implements HashService {

  private static final int LENGTH = 7;

  @Override
  public String hash(String url) throws NoSuchAlgorithmException {
    MessageDigest sha256Hash = MessageDigest.getInstance("SHA-256");
    byte[] sha256EncodedHash = sha256Hash.digest(url.getBytes(StandardCharsets.UTF_8));
    String encoded = Base64.getEncoder().encodeToString(sha256EncodedHash);

    return encoded.substring(0, LENGTH).toUpperCase();
  }
}
