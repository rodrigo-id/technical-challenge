package cl.meli.technicalchallenge.mock;

import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import java.util.Date;

public class LogEntityMock {

  public static LogEntity buildForTest() {
    LogEntity logEntity = new LogEntity();
    logEntity.setShortUrlVisited("http://localhost:8080/QAZXSWE");
    logEntity.setVisitedDate(new Date());
    logEntity.setActive(true);
    logEntity.setId(1L);

    return logEntity;
  }
}
