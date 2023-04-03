package cl.meli.technicalchallenge.mock;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;

public class UrlDomainModelMock {

  public static UrlDomainModel buildForTest() {
    return UrlDomainModel.builder()
        .setLongUrl("https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api")
        .setShortUrl("http://localhost:8080/QAZXSWE")
        .setIsPresent(true)
        .build();
  }
}
