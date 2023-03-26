package cl.meli.technicalchallenge.mock;

import cl.meli.technicalchallenge.infraestructure.data.entity.UrlEntity;
import java.util.Date;

public class UrlEntityMock {

  public static UrlEntity buildForTest() {
    UrlEntity entity = new UrlEntity();
    entity.setLongUrl("https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api");
    entity.setShortUrl("http://localhost:8080/297199582");
    entity.setCreatedDate(new Date());
    entity.setId(1l);
    return entity;
  }
}
