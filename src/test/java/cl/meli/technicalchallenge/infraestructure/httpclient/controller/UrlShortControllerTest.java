package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.mapper.UrlLogResponseMapper;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLogResponse;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLongResponse;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import java.text.MessageFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@ExtendWith(MockitoExtension.class)
class UrlShortControllerTest {

  UrlShortController urlShortController;

  @Mock
  UrlLongUseCase urlLongUseCase;
  @Mock
  UrlLogUseCase urlLogUseCase;
  @Spy
  UrlLogResponseMapper urlLogResponseMapper;


  @BeforeEach
  void setUp() {
    urlShortController = new UrlShortController(urlLongUseCase, urlLogUseCase, urlLogResponseMapper);
  }

  @Test
  void givenRedirectToUrl_whenAShortUrlIsProvided_thenRedirectToExpectedUrl() {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    String shortUrl = "http://localhost";
    when(urlLongUseCase.retrieveLongUrl(shortUrl))
        .thenReturn(urlDomainModelMock.getLongUrl());

    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    HttpServletResponse httpServletResponse = new MockHttpServletResponse();

    urlShortController.redirectToUrl(httpServletRequest,httpServletResponse);

    verify(urlLogUseCase, times(1)).save(shortUrl);
    Assertions.assertEquals(302, httpServletResponse.getStatus());
    Assertions.assertEquals(urlDomainModelMock.getLongUrl(), httpServletResponse.getHeader("location"));
    Assertions.assertEquals("close", httpServletResponse.getHeader("connection"));
  }

  @Test
  void givenUrlOrigen_whenShortUrlIsProvided_thenReturnTheOriginalUrl() {
    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    String serverBaseUrl = String.valueOf(httpServletRequest.getRequestURL());
    String hashGenerated = "QAZXSWE";
    String shortUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    when(urlLongUseCase.retrieveLongUrl(shortUrl))
        .thenReturn(urlDomainModelMock.getLongUrl());

    ResponseEntity<UrlLongResponse> urlResponseEntity =
        urlShortController.getOriginalUrl(httpServletRequest, hashGenerated);

    Assertions.assertEquals(urlResponseEntity.getBody().getLongUrl(), urlDomainModelMock.getLongUrl());
  }

  @Test
  void givenUrlInfo_whenShortUrlIsActive_thenReturnTheLogInfoForTheUrl() {
    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    String serverBaseUrl = String.valueOf(httpServletRequest.getRequestURL());
    String hashGenerated = "QAZXSWE";
    String shortUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    LogDomainModel logDomainModelMock = LogDomainModelMock.buildNewModelForTest();
    List<LogDomainModel> urlDomainModelList = List.of(logDomainModelMock);
    when(urlLogUseCase.retrieveShortUrlInfo(shortUrl))
        .thenReturn(urlDomainModelList);
    ResponseEntity<UrlLogResponse> urlLogResponse = urlShortController.getUrlInfo(httpServletRequest, "QAZXSWE");

    Assertions.assertTrue(urlLogResponse.getBody().getVisitedList().size() > 0);
    Assertions.assertTrue(urlLogResponse.getBody().getStatus().isActive());
    Assertions.assertNull(urlLogResponse.getBody().getStatus().getDeactivateDate());
  }

  @Test
  void givenUrlInfo_whenShortUrlIsNotActive_thenReturnTheLogInfoForTheUrl() {
    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    String serverBaseUrl = String.valueOf(httpServletRequest.getRequestURL());
    String hashGenerated = "QAZXSWE";
    String shortUrl = MessageFormat.format("{0}/{1}", serverBaseUrl, hashGenerated);

    LogDomainModel logDomainModelMock = LogDomainModelMock.buildDeactivateModelForTest();
    List<LogDomainModel> urlDomainModelList = List.of(logDomainModelMock);
    when(urlLogUseCase.retrieveShortUrlInfo(shortUrl))
        .thenReturn(urlDomainModelList);
    ResponseEntity<UrlLogResponse> urlLogResponse = urlShortController.getUrlInfo(httpServletRequest, "QAZXSWE");

    Assertions.assertFalse(urlLogResponse.getBody().getStatus().isActive());
    Assertions.assertNotNull(urlLogResponse.getBody().getStatus().getDeactivateDate());
  }
}