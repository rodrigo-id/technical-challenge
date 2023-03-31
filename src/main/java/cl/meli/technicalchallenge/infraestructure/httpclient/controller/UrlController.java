package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlRequest;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {
  private final UrlShortUseCase urlShortUseCase;
  private final UrlDeleteUseCase urlDeleteUseCase;

  public UrlController(UrlShortUseCase urlShortUseCase, UrlDeleteUseCase urlDeleteUseCase) {
    this.urlShortUseCase = urlShortUseCase;
    this.urlDeleteUseCase = urlDeleteUseCase;
  }

  @PostMapping("/create")
  public ResponseEntity<UrlResponse> createShortUrl(
      HttpServletRequest httpServletRequest,
      @RequestBody @Valid UrlRequest urlRequest) {

    final String serverUrl = convertToServerUrl(
        httpServletRequest.getRequestURI(),
        httpServletRequest.getRequestURL().toString());

    String shortUrl = urlShortUseCase.createShortUrl(urlRequest.getUrl(), serverUrl);

    UrlResponse urlResponse = new UrlResponse(shortUrl);

    return new ResponseEntity<>(urlResponse, HttpStatus.CREATED);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteShortUrl(@RequestBody @Valid UrlRequest urlRequest) {
    urlDeleteUseCase.deleteShortUrl(urlRequest.getUrl());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private String convertToServerUrl(String uri, String url) {
    return url.replace(uri, "");
  }
}
