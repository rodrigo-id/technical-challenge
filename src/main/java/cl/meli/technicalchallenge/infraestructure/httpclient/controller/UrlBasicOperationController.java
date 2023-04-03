package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.NotificationError;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlRequest;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlShortResponse;
import cl.meli.technicalchallenge.shared.utils.UrlConverterUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlBasicOperationController {
  private final UrlShortUseCase urlShortUseCase;
  private final UrlDeleteUseCase urlDeleteUseCase;
  private UrlConverterUtil urlConverterUtil;
  private final UrlLogUseCase urlLogUseCase;

  public UrlBasicOperationController(UrlShortUseCase urlShortUseCase, UrlDeleteUseCase urlDeleteUseCase,
                                     UrlLogUseCase urlLogUseCase) {
    this.urlShortUseCase = urlShortUseCase;
    this.urlDeleteUseCase = urlDeleteUseCase;
    this.urlLogUseCase = urlLogUseCase;
    this.urlConverterUtil = UrlConverterUtil.getInstance();
  }

  @Operation(summary = "crear url corta", description = "Crea una url corta a partir de una larga")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "creación exitosa"),
      @ApiResponse(responseCode = "400", description = "Request no válido", content = @Content(schema = @Schema(implementation = NotificationError.class))),
      @ApiResponse(responseCode = "500", description = "Error interno al crear o guardar la url corta", content = @Content(schema = @Schema(implementation = NotificationError.class)))
  })
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


  @Operation(summary = "Borrar url", description = "Borra la url corta y su url larga asociada en DB")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Operación exitosa"),
      @ApiResponse(responseCode = "400", description = "Requet no válido", content = @Content(schema = @Schema(implementation = NotificationError.class))),
      @ApiResponse(responseCode = "404", description = "Url no encontrada", content = @Content(schema = @Schema(implementation = NotificationError.class)))
  })
  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteShortUrl(@RequestBody @Valid UrlRequest urlRequest) {
    urlDeleteUseCase.deleteShortUrl(urlRequest.getUrl());
    urlLogUseCase.deactivate(urlRequest.getUrl());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
