package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlLogUseCaseImplTest {

  UrlLogUseCaseImpl urlLogUseCase;

  @Mock
  LogDomainRepository logDomainRepository;

  @BeforeEach
  void setUp() {
    urlLogUseCase = new UrlLogUseCaseImpl(logDomainRepository);
  }

  @Test
  void givenAUrlIsProvided_whenMethodSave_thenOk() {
    LogDomainModel logDomainModelMock = LogDomainModelMock.buildNewModelForTest();
    urlLogUseCase.save(logDomainModelMock.getShortUrlVisited());

    /*verify(
        logDomainRepository,
        times(1)).save(logDomainModelMock);*/
  }

  @Test
  void givenAUrlIsProvided_whenMethodDeactivate_thenOk() {
    LogDomainModel logDomainModelMock = LogDomainModelMock.buildNewModelForTest();
    urlLogUseCase.deactivate(logDomainModelMock.getShortUrlVisited());

    /*verify(
        logDomainRepository,
        times(1)).save(logDomainModelMock);*/
  }
}