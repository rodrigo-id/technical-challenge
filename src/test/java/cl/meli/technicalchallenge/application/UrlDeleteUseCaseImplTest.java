package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.error.DomainException;
import javax.persistence.Lob;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlDeleteUseCaseImplTest {

  UrlDeleteUseCaseImpl urlDeleteUseCase;

  @Mock
  UrlDomainRepository urlDomainRepository;

  @BeforeEach
  void setUp() {
    urlDeleteUseCase = new UrlDeleteUseCaseImpl(urlDomainRepository);
  }

  @Test
  void givenDeleteAUrl_whenGiveAShortUrl_thenTheNumberOfDeletedResultIsMayorToZero() {
    String shortUrl = "http://short.url";
    when(urlDomainRepository.deleteShortUrl(shortUrl)).thenReturn(1L);

    urlDeleteUseCase.deleteShortUrl(shortUrl);

    verify(
        urlDomainRepository,
        times(1)).deleteShortUrl(shortUrl);
  }

  @Test
  void givenDeleteUrl_whenUrlIsNotDelete_thenThrowDomainException() {
    String shortUrl = "";

    Assertions.assertThrows(
        DomainException.class,
        () -> urlDeleteUseCase.deleteShortUrl(shortUrl));
  }
}