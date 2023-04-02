package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlRequest;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlShortResponse;
import cl.meli.technicalchallenge.shared.utils.UrlConverterUtil;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortController {
  private final UrlShortUseCase urlShortUseCase;
  private final UrlDeleteUseCase urlDeleteUseCase;
  private UrlConverterUtil urlConverterUtil;
  private final UrlLogUseCase urlLogUseCase;

  public UrlShortController(UrlShortUseCase urlShortUseCase, UrlDeleteUseCase urlDeleteUseCase,
                            UrlLogUseCase urlLogUseCase) {
    this.urlShortUseCase = urlShortUseCase;
    this.urlDeleteUseCase = urlDeleteUseCase;
    this.urlLogUseCase = urlLogUseCase;
    this.urlConverterUtil = UrlConverterUtil.getInstance();
  }

  @PostMapping("/create")
  public ResponseEntity<UrlShortResponse> createShortUrl(
      HttpServletRequest httpServletRequest,
      @RequestBody @Valid UrlRequest urlRequest) {

    final String serverUrl = urlConverterUtil.convertToServerUrl(
        httpServletRequest.getRequestURI(),
        httpServletRequest.getRequestURL().toString());

    String shortUrl = urlShortUseCase.createShortUrl(urlRequest.getUrl(), serverUrl);

    UrlShortResponse urlResponse = new UrlShortResponse(shortUrl);

    return new ResponseEntity<>(urlResponse, HttpStatus.CREATED);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteShortUrl(@RequestBody @Valid UrlRequest urlRequest) {
    urlDeleteUseCase.deleteShortUrl(urlRequest.getUrl());
    urlLogUseCase.deactivate(urlRequest.getUrl());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
