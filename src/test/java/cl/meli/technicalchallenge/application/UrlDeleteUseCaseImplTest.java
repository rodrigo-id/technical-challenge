package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import cl.meli.technicalchallenge.shared.utils.ValidatorUtil;
import cl.meli.technicalchallenge.shared.error.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlDeleteUseCaseImplTest {

  UrlDeleteUseCaseImpl urlDeleteUseCase;

  @Mock
  UrlDomainRepository urlDomainRepository;
  @Spy
  ValidatorUtil validatorUtil;

  UrlDomainModel urlDomainModel;

  @BeforeEach
  void setUp() {
    urlDeleteUseCase = new UrlDeleteUseCaseImpl(urlDomainRepository, validatorUtil);
    urlDomainModel = UrlDomainModelMock.buildForTest();
  }

  @Test
  void givenDeleteAUrl_whenGiveAValidShortUrl_thenTheNumberOfDeletedResultIsMayorToZero() {
    when(urlDomainRepository.findUrlByShortUrl(urlDomainModel.getShortUrl())).thenReturn(urlDomainModel);
    when(urlDomainRepository.deleteShortUrl(urlDomainModel)).thenReturn(1L);

    urlDeleteUseCase.deleteShortUrl(urlDomainModel.getShortUrl());

    verify(
        urlDomainRepository,
        times(1)).deleteShortUrl(urlDomainModel);
  }

  @Test
  void givenDeleteUrl_whenTheUrlIsNotValid_thenThrowDomainException() {
    String shortUrl = "ht://lo-js";

    Assertions.assertThrows(
        DomainException.class,
        () -> urlDeleteUseCase.deleteShortUrl(shortUrl));
  }

  @Test
  void givenDeleteUrl_whenUrlDoesNotExistInDb_thenThrowDomainException() {
    String shortUrl = "http://lo-js.com";
    when(urlDomainRepository.findUrlByShortUrl(shortUrl)).thenReturn(UrlDomainModel.builder().build());

    Assertions.assertThrows(
        DomainException.class,
        () -> urlDeleteUseCase.deleteShortUrl(shortUrl));

  }

  @Test
  void givenDeleteUrl_whenUrlCannotByDelete_thenThrowDomainException() {
    String shortUrl = "http://lo-js.com";
    when(urlDomainRepository.findUrlByShortUrl(shortUrl)).thenReturn(urlDomainModel);
    when(urlDomainRepository.deleteShortUrl(urlDomainModel)).thenReturn(0L);

    Assertions.assertThrows(
        DomainException.class,
        () -> urlDeleteUseCase.deleteShortUrl(shortUrl));

  }
}