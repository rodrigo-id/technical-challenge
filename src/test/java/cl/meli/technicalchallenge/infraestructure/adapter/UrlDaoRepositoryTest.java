package cl.meli.technicalchallenge.infraestructure.adapter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.UrlRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.relational.UrlRepository;
import cl.meli.technicalchallenge.infraestructure.data.relational.entity.UrlEntity;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import cl.meli.technicalchallenge.mock.UrlEntityMock;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@ExtendWith(MockitoExtension.class)
class UrlDaoRepositoryTest {
  UrlDaoRepository urlDaoRepository;
  @Mock
  UrlRepository urlRepository;
  @Mock
  UrlRepositoryMapper urlRepositoryMapper;
  @Mock
  CacheManager cacheManager;
  UrlDomainModel urlDomainModel;
  UrlEntity urlEntity;

  @BeforeEach
  void setUp() {
    urlDaoRepository = new UrlDaoRepository(urlRepository, urlRepositoryMapper, cacheManager);
    urlDomainModel = UrlDomainModelMock.buildForTest();
    urlEntity = UrlEntityMock.buildForTest();
  }

  @Test
  void givenCallEntityRepository_whenSaveNewEntity_thenOk() {
    when(urlRepositoryMapper.toUrlEntity(urlDomainModel)).thenReturn(urlEntity);

    urlDaoRepository.saveUrl(urlDomainModel);

    verify(
        urlRepository,
        times(1)).save(urlEntity);
  }

  @Test
  void givenCallEntityRepository_whenSearchByLongUrl_thenUrlDomainModel() {
    String longUrl = urlDomainModel.getLongUrl();
    when(urlRepository.findByLongUrl(longUrl))
        .thenReturn(Optional.of(urlEntity));

    when(urlRepositoryMapper.toUrlDomainModel(Optional.of(urlEntity)))
        .thenReturn(urlDomainModel);

    UrlDomainModel response = urlDaoRepository.findUrlByLongUrl(longUrl);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(urlDomainModel.getLongUrl(), response.getLongUrl());
    Assertions.assertEquals(urlDomainModel.getShortUrl(), response.getShortUrl());
  }

  @Test
  void givenCallEntityRepository_whenSearchByShortUrl_thenUrlDomainModel() {
    String longUrl = urlDomainModel.getLongUrl();
    when(urlRepository.findByShortUrl(longUrl))
        .thenReturn(Optional.of(urlEntity));

    when(urlRepositoryMapper.toUrlDomainModel(Optional.of(urlEntity)))
        .thenReturn(urlDomainModel);

    UrlDomainModel response = urlDaoRepository.findUrlByShortUrl(longUrl);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(urlDomainModel.getLongUrl(), response.getLongUrl());
    Assertions.assertEquals(urlDomainModel.getShortUrl(), response.getShortUrl());
  }

  //@Test
  void givenDeleteAUrl_whenTheShortUrlIsFound_thenReturnTheRowsAffectedNumber() {
    when(urlRepository.deleteByShortUrl(urlDomainModel.getShortUrl()))
        .thenReturn(1L);

    Long result = urlDaoRepository.deleteShortUrl(urlDomainModel);

    Assertions.assertTrue(result > 0);
  }

  //@Test
  void givenDeleteAUrl_whenTheShortUrlIsNotFound_thenReturnZero() {
    when(urlRepository.deleteByShortUrl(urlDomainModel.getShortUrl()))
        .thenReturn(0L);

    Long result = urlDaoRepository.deleteShortUrl(urlDomainModel);

    Assertions.assertEquals(0, result);
  }
}