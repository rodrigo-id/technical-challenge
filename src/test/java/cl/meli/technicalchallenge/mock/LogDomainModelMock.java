package cl.meli.technicalchallenge.mock;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import java.time.LocalDateTime;

public class LogDomainModelMock {

  private static final String SHORT_URL = "http://localhost:8080/QAZXSWE";

  public static LogDomainModel buildNewModelForTest() {
    return LogDomainModel.builder()
        .setIsActive(true)
        .setVisitedDate(LocalDateTime.now())
        .setShortUrlVisited(SHORT_URL)
        .build();
  }

  public static LogDomainModel buildDeactivateModelForTest() {
    return LogDomainModel.builder()
        .setIsActive(false)
        .setDeactivateDate(LocalDateTime.now())
        .setShortUrlVisited(SHORT_URL)
        .build();
  }
}
