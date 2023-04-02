package cl.meli.technicalchallenge.infraestructure.adapter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.LogRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.LogRepository;
import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import cl.meli.technicalchallenge.mock.LogEntityMock;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogDaoRepositoryTest {

  LogDaoRepository logDaoRepository;

  @Mock
  LogRepository logRepository;

  @Mock
  LogRepositoryMapper logRepositoryMapper;

  @BeforeEach
  void setUp() {
    logDaoRepository = new LogDaoRepository(logRepository, logRepositoryMapper);
  }

  @Test
  void givenSaveLogEntity_whenMethodSave_thenOk() {
    LogEntity logEntityMock = LogEntityMock.buildForTest();
    LogDomainModel logDomainModelMock = LogDomainModelMock.buildNewModelForTest();

    when(logRepositoryMapper.toLogEntity(logDomainModelMock)).thenReturn(logEntityMock);

    logDaoRepository.saveLongInfo(logDomainModelMock);

    verify(
        logRepository,
        times(1)).save(logEntityMock);
  }

  @Test
  void givenRetrieveShortUrlInfo_whenPassAShortUrl_thenReturnListOfDomainLogUrlInfo() {
    List<LogEntity> logEntityListMock = List.of(LogEntityMock.buildForTest());
    List<LogDomainModel> logDomainModelListMock = List.of(LogDomainModelMock.buildNewModelForTest());

    String shortUrl = "http://localhost:8080/QAZXSWE";
    when(logRepository.findByShortUrlVisited(shortUrl)).thenReturn(logEntityListMock);
    when(logRepositoryMapper.toLogDomainModelList(logEntityListMock)).thenReturn(logDomainModelListMock);

    List<LogDomainModel> logDomainModelList = logDaoRepository.retrieveShortUrlInfo(shortUrl);

    Assertions.assertTrue(logDomainModelList.size() > 0);
    Assertions.assertEquals(
        logDomainModelListMock.get(0).getShortUrlVisited(),
        logDomainModelList.get(0).getShortUrlVisited());
  }
}