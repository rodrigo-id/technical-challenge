package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import cl.meli.technicalchallenge.shared.error.DomainException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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

  @Test
  void givenNoUrlFound_whenSearchForShortUrl_thenThrowDomainException() {
    when(logDomainRepository.retrieveShortUrlInfo("")).thenReturn(new ArrayList<>());

    Assertions.assertThrows(
        DomainException.class,
        () -> urlLogUseCase.retrieveShortUrlInfo(""));
  }

  @Test
  void givenFoundUrl_whenSearchForShortUrl_thenReturnLogDomainModelList() {
    LogDomainModel logDomainModelMock = LogDomainModelMock.buildNewModelForTest();
    List<LogDomainModel> logDomainModelList = List.of(logDomainModelMock);

    when(logDomainRepository.retrieveShortUrlInfo(logDomainModelMock.getShortUrlVisited()))
        .thenReturn(logDomainModelList);

    List<LogDomainModel> response = urlLogUseCase.retrieveShortUrlInfo(logDomainModelMock.getShortUrlVisited());

    Assertions.assertEquals(logDomainModelList.get(0).getShortUrlVisited(), response.get(0).getShortUrlVisited());
    Assertions.assertEquals(logDomainModelList.get(0).isActive(), response.get(0).isActive());
    Assertions.assertEquals(logDomainModelList.get(0).getDeactivateDate(), response.get(0).getDeactivateDate());
  }
}