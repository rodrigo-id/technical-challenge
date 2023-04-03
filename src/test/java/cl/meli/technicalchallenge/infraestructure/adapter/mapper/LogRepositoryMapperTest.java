package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogRepositoryMapperTest {

  LogRepositoryMapper logRepositoryMapper = new LogRepositoryMapper();

  @Test
  void givenLogRepositoryMapper_whenGiveAUrl_thenConvertToLogEntity() {
    LogDomainModel logDomainModelMock = LogDomainModelMock.buildNewModelForTest();
    LogEntity logEntity = logRepositoryMapper.toLogEntity(logDomainModelMock);

    Assertions.assertEquals(logDomainModelMock.getShortUrlVisited(), logEntity.getShortUrlVisited());
  }

}