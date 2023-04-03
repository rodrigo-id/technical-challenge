package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.infraestructure.data.entities.UrlEntity;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import cl.meli.technicalchallenge.mock.UrlEntityMock;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UrlRepositoryMapperTest {

  UrlRepositoryMapper urlRepositoryMapper = new UrlRepositoryMapper();

  @Test
  void givenRepositoryMapper_whenAUrlDomainModelIsGiven_thenConvertToAEntityUrl() {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    UrlEntity urlEntity = urlRepositoryMapper.toUrlEntity(urlDomainModelMock);

    Assertions.assertNotNull(urlEntity);
    Assertions.assertEquals(urlDomainModelMock.getShortUrl(), urlEntity.getShortUrl());
    Assertions.assertEquals(urlDomainModelMock.getLongUrl(), urlEntity.getLongUrl());
  }

  @Test
  void givenRepositoryMapper_whenAUrlEntityIsGiven_thenConvertToAUrlDomainModel() {
    UrlEntity urlEntityMock = UrlEntityMock.buildForTest();
    UrlDomainModel urlDomainModel = urlRepositoryMapper.toUrlDomainModel(Optional.of(urlEntityMock));

    Assertions.assertNotNull(urlDomainModel);
    Assertions.assertEquals(urlEntityMock.getLongUrl(), urlDomainModel.getLongUrl());
    Assertions.assertEquals(urlEntityMock.getShortUrl(), urlDomainModel.getShortUrl());
    Assertions.assertTrue(urlDomainModel.isPresent());
  }

}