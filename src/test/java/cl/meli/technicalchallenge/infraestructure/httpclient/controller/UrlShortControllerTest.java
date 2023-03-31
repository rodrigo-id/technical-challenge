package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlRequest;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlShortResponse;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

@ExtendWith(MockitoExtension.class)
class UrlShortControllerTest {

  UrlShortController urlShortController;

  @Mock
  UrlShortUseCase urlShortUseCase;
  @Mock
  UrlDeleteUseCase urlDeleteUseCase;
  UrlRequest urlRequest = new UrlRequest();

  @BeforeEach
  void setUp() {
    urlShortController = new UrlShortController(urlShortUseCase, urlDeleteUseCase);
    urlRequest.setUrl("http://www.soyunaurl.com/usada-como-prueba");
  }

  @Test
  void givenUrlController_whenProvideALongUrl_thenReturnTheUrlShorted() {
    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    String serverBaseUrl = String.valueOf(httpServletRequest.getRequestURL());
    String hashGenerated = "QAZXSWE";
    String serverUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    when(urlShortUseCase.createShortUrl(urlRequest.getUrl(), serverBaseUrl))
        .thenReturn(serverUrl);

    ResponseEntity<UrlShortResponse> urlResponseEntity = urlShortController
        .createShortUrl(httpServletRequest, urlRequest);

    Assertions.assertEquals(urlResponseEntity.getBody().getShortUrl(), serverUrl);
  }

  @Test
  void givenUrlController_whenDeleteAShortUrl_thenDeleteWithAStatusCode200() {
    ResponseEntity<Void> response = urlShortController.deleteShortUrl(urlRequest);

    Assertions.assertEquals(200, response.getStatusCode().value());
    verify(urlDeleteUseCase, times(1)).deleteShortUrl(urlRequest.getUrl());
  }
}