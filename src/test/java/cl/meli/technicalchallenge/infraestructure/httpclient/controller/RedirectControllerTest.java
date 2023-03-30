package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import static org.mockito.Mockito.when;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.mock.UrlDomainModelMock;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@ExtendWith(MockitoExtension.class)
class RedirectControllerTest {

  RedirectController redirectController;

  @Mock
  UrlLongUseCase urlLongUseCase;


  @BeforeEach
  void setUp() {
    redirectController = new RedirectController(urlLongUseCase);
  }

  @Test
  void givenRedirectToUrl_whenAShortUrlIsProvided_thenRedirectToExpectedUrl() {
    UrlDomainModel urlDomainModelMock = UrlDomainModelMock.buildForTest();
    when(urlLongUseCase.retrieveLongUrl("http://localhost"))
        .thenReturn(urlDomainModelMock.getLongUrl());

    HttpServletRequest httpServletRequest = new MockHttpServletRequest();
    HttpServletResponse httpServletResponse = new MockHttpServletResponse();

    redirectController.redirectToUrl(httpServletRequest,httpServletResponse);

    Assertions.assertEquals(302, httpServletResponse.getStatus());
    Assertions.assertEquals(urlDomainModelMock.getLongUrl(), httpServletResponse.getHeader("location"));
    Assertions.assertEquals("close", httpServletResponse.getHeader("connection"));
  }
}