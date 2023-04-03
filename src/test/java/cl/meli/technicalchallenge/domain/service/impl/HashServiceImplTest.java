package cl.meli.technicalchallenge.domain.service.impl;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashServiceImplTest {

  HashServiceImpl hashService = new HashServiceImpl();

  @Test
  void givenUrl_whenHashTheUrl_thenReturnUrlHashed() throws NoSuchAlgorithmException {
    String url = "www.soyunaurl.com/probando-el-hash";
    String response = hashService.hash(url);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(7, response.length());
  }

}