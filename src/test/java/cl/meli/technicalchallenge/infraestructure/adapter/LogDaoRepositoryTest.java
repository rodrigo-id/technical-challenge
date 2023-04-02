package cl.meli.technicalchallenge.infraestructure.adapter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.LogRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.urlstorage.LogRepository;
import cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities.LogEntity;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import cl.meli.technicalchallenge.mock.LogEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

    logDaoRepository.save(logDomainModelMock);

    verify(
        logRepository,
        times(1)).save(logEntityMock);
  }
}