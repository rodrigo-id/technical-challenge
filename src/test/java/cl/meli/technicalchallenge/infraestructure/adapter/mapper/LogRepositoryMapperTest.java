package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities.LogEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogRepositoryMapperTest {

  LogRepositoryMapper logRepositoryMapper = new LogRepositoryMapper();
  @Test
  void givenLogRepositoryMapper_whenGiveAUrl_thenConvertToLogEntity() {
    String shortUrl = "http://localhost:8080/QAZXSWE";
    LogEntity logEntity = logRepositoryMapper.toLogEntity(shortUrl);

    Assertions.assertEquals(shortUrl, logEntity.getShortUrlVisited());
  }

}