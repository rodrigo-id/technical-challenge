package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.domain.service.HashService;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import cl.meli.technicalchallenge.shared.utils.ValidatorUtil;
import cl.meli.technicalchallenge.shared.error.DomainException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlShortUseCaseImplTest {

  UrlShortUseCaseImpl urlShortUseCase;
  @Mock
  UrlDomainRepository urlDomainRepository;
  @Spy
  ValidatorUtil validatorUtil;
  @Mock
  HashService hashService;

  @BeforeEach
  void setUp() {
    urlShortUseCase = new UrlShortUseCaseImpl(urlDomainRepository, validatorUtil, hashService);
  }

  @Test
  void givenProvideABadUrl_whenValidateTheUrl_thenThrowADomainException() {
    Assertions.assertThrows(
        DomainException.class,
        () -> urlShortUseCase.createShortUrl("", ""));
  }

  @Test
  void givenProvideALongUrl_whenSearchTheUrlIfExistsInDb_thenReturnExistingShortUrl() {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    String longUrl = urlDomainModelMock.getLongUrl();
    String serverUrl = "http://localhost:8080";
    when(urlDomainRepository.findUrlByLongUrl(longUrl)).thenReturn(urlDomainModelMock);

    String response = urlShortUseCase.createShortUrl(longUrl, serverUrl);

    Assertions.assertEquals(urlDomainModelMock.getShortUrl(), response);
  }

  @Test
  void givenProvideALongUrl_whenTheUrlDoesNotExistInDb_thenSaveNewUrlEntityAndReturnShortUrl()
      throws NoSuchAlgorithmException {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    String longUrl = urlDomainModelMock.getLongUrl();
    String serverBaseUrl = "http://localhost:8080";
    String hashGenerated = "QAZXSWE";
    String serverUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    when(urlDomainRepository.findUrlByLongUrl(longUrl))
        .thenReturn(UrlDomainModel.builder().build());
    when(hashService.hash(longUrl)).thenReturn(hashGenerated);

    String response = urlShortUseCase.createShortUrl(longUrl, serverBaseUrl);

    Assertions.assertEquals(serverUrl, response);
  }

  @Test
  void givenProvideALongUrl_whenHashServiceFails_thenReturnADomainException()
      throws NoSuchAlgorithmException {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    String longUrl = urlDomainModelMock.getLongUrl();
    String serverBaseUrl = "http://localhost:8080";
    String hashGenerated = "QAZXSWE";
    String serverUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    when(urlDomainRepository.findUrlByLongUrl(longUrl))
        .thenReturn(UrlDomainModel.builder().build());
    when(hashService.hash(longUrl)).thenThrow(NoSuchAlgorithmException.class);

    Assertions.assertThrows(
        DomainException.class,
        () -> urlShortUseCase.createShortUrl(urlDomainModelMock.getLongUrl(), serverUrl));
  }
}