package cl.meli.technicalchallenge.mock;

import cl.meli.technicalchallenge.infraestructure.data.entities.UrlEntity;
import java.time.LocalDateTime;

public class UrlEntityMock {

  public static UrlEntity buildForTest() {
    UrlEntity entity = new UrlEntity();
    entity.setLongUrl("https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api");
    entity.setShortUrl("http://localhost:8080/297199582");
    entity.setCreatedDate(LocalDateTime.now());
    entity.setId(1l);
    return entity;
  }
}
