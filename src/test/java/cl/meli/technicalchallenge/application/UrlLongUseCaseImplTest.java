package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import cl.meli.technicalchallenge.shared.error.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlLongUseCaseImplTest {

  UrlLongUseCaseImpl urlLongUseCase;
  @Mock
  UrlDomainRepository urlDomainRepository;

  @BeforeEach
  void setUp() {
    urlLongUseCase = new UrlLongUseCaseImpl(urlDomainRepository);
  }

  @Test
  void givenSearchUrl_whenTheShortUrlIsProvided_thenReturnTheLongUrl() {
    UrlDomainModel urlDomainModel = UrlDomainModelMock.buildForTest();
    String shortUrl = urlDomainModel.getShortUrl();
    when(urlDomainRepository.findUrlByShortUrl(shortUrl)).thenReturn(urlDomainModel);

    String longUrl = urlLongUseCase.retrieveLongUrl(shortUrl);

    Assertions.assertEquals(urlDomainModel.getLongUrl(), longUrl);
  }

  @Test
  void givenSearchUrl_whenTheShortUrlNotFound_thenTrowADomainException() {
    String shortUrl = "http://not.found";
    when(urlDomainRepository.findUrlByShortUrl(shortUrl)).thenReturn(UrlDomainModel.builder().build());
    Assertions.assertThrows(
        DomainException.class,
        () -> urlLongUseCase.retrieveLongUrl(shortUrl));
  }
}