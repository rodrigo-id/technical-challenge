package cl.meli.technicalchallenge.domain.service;

import java.security.NoSuchAlgorithmException;

public interface HashService {

  String hash(String url) throws NoSuchAlgorithmException;
}
