package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.mapper.UrlLogResponseMapper;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLongResponse;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@ExtendWith(MockitoExtension.class)
class UrlLongControllerTest {

  UrlLongController urlLongController;

  @Mock
  UrlLongUseCase urlLongUseCase;
  @Mock
  UrlLogUseCase urlLogUseCase;
  @Mock
  UrlLogResponseMapper urlLogResponseMapper;


  @BeforeEach
  void setUp() {
    urlLongController = new UrlLongController(urlLongUseCase, urlLogUseCase, urlLogResponseMapper);
  }

  @Test
  void givenRedirectToUrl_whenAShortUrlIsProvided_thenRedirectToExpectedUrl() {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    String shortUrl = "http://localhost";
    when(urlLongUseCase.retrieveLongUrl(shortUrl))
        .thenReturn(urlDomainModelMock.getLongUrl());

    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    HttpServletResponse httpServletResponse = new MockHttpServletResponse();

    urlLongController.redirectToUrl(httpServletRequest,httpServletResponse);

    verify(urlLogUseCase, times(1)).save(shortUrl);
    Assertions.assertEquals(302, httpServletResponse.getStatus());
    Assertions.assertEquals(urlDomainModelMock.getLongUrl(), httpServletResponse.getHeader("location"));
    Assertions.assertEquals("close", httpServletResponse.getHeader("connection"));
  }

  @Test
  void givenUrlInfo_whenShortUrlIsProvided_thenReturnTheOriginalUrl() {
    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    String serverBaseUrl = String.valueOf(httpServletRequest.getRequestURL());
    String hashGenerated = "QAZXSWE";
    String shortUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    when(urlLongUseCase.retrieveLongUrl(shortUrl))
        .thenReturn(urlDomainModelMock.getLongUrl());

    ResponseEntity<UrlLongResponse> urlResponseEntity =
        urlLongController.getOriginalUrl(httpServletRequest, hashGenerated);

    Assertions.assertEquals(urlResponseEntity.getBody().getLongUrl(), urlDomainModelMock.getLongUrl());
  }
}