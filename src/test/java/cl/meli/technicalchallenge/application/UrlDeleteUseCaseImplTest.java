package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.ValidatorUtils;
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
  ValidatorUtils validatorUtils;

  @BeforeEach
  void setUp() {
    urlDeleteUseCase = new UrlDeleteUseCaseImpl(urlDomainRepository, validatorUtils);
  }

  @Test
  void givenDeleteAUrl_whenGiveAValidShortUrl_thenTheNumberOfDeletedResultIsMayorToZero() {
    String shortUrl = "http://www.shurl.cl";
    when(urlDomainRepository.deleteShortUrl(shortUrl)).thenReturn(1L);

    urlDeleteUseCase.deleteShortUrl(shortUrl);

    verify(
        urlDomainRepository,
        times(1)).deleteShortUrl(shortUrl);
  }

  @Test
  void givenDeleteUrl_whenTheUrlIsNotValid_thenThrowDomainException() {
    String shortUrl = "";

    Assertions.assertThrows(
        DomainException.class,
        () -> urlDeleteUseCase.deleteShortUrl(shortUrl));
  }
}