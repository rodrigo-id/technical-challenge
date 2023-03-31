package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLongResponse;
import cl.meli.technicalchallenge.shared.utils.UrlConverterUtil;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlLongController {

  private final UrlLongUseCase urlLongUseCase;
  private UrlConverterUtil urlConverterUtil;

  public UrlLongController(UrlLongUseCase urlLongUseCase) {
    this.urlLongUseCase = urlLongUseCase;
    this.urlConverterUtil = UrlConverterUtil.getInstance();
  }

  @GetMapping("/{urlShorted}")
  public void redirectToUrl(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {

    String originalUrl = urlLongUseCase.retrieveLongUrl(httpServletRequest.getRequestURL().toString());
    httpServletResponse.setStatus(302);
    httpServletResponse.setHeader(HttpHeaders.LOCATION, originalUrl);
    httpServletResponse.setHeader(HttpHeaders.CONNECTION, "close");
  }

  @GetMapping("/{urlHash}/info")
  public ResponseEntity<UrlLongResponse> getOriginalUrl(
      HttpServletRequest httpServletRequest,
      @PathVariable String urlHash) {

    final String serverUrl = urlConverterUtil.convertToServerUrl(
        httpServletRequest.getRequestURI(),
        httpServletRequest.getRequestURL().toString());

    String shortUrl = MessageFormat.format("{0}/{1}", serverUrl, urlHash);

    String originalUrl = urlLongUseCase.retrieveLongUrl(shortUrl);

    UrlLongResponse urlResponse = new UrlLongResponse(originalUrl);
    return new ResponseEntity<>(urlResponse, HttpStatus.OK);
  }

}
