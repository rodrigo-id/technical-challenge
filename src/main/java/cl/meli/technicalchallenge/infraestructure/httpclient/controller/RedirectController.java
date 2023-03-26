package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

  private final UrlLongUseCase urlLongUseCase;

  public RedirectController(UrlLongUseCase urlLongUseCase) {
    this.urlLongUseCase = urlLongUseCase;
  }

  @GetMapping("/{urlShorted}")
  public void redirectToUrl(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {

    String originalUrl = urlLongUseCase.retrieveLongUrl(httpServletRequest.getRequestURL().toString());
    httpServletResponse.setHeader("Location", originalUrl);
    httpServletResponse.setStatus(302);
  }
}
