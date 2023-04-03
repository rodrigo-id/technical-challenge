package cl.meli.technicalchallenge.infraestructure.httpclient.controller;

import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.infraestructure.httpclient.mapper.LogVisitResponseMapper;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.LogVisitResponse;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.NotificationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlLogController {
  private final UrlLogUseCase urlLogUseCase;
  private final LogVisitResponseMapper logVisitResponseMapper;

  public UrlLogController(UrlLogUseCase urlLogUseCase, LogVisitResponseMapper logVisitResponseMapper) {
    this.urlLogUseCase = urlLogUseCase;
    this.logVisitResponseMapper = logVisitResponseMapper;
  }

  @Operation(summary = "Info visitas de url cortas", description = "Se obtiene informacion por cada url corta que se haya creado y si esta esta activa o no")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Información encontrada"),
      @ApiResponse(responseCode = "500", description = "Errores no controlados", content = @Content(schema = @Schema(implementation = NotificationError.class)))
  })
  @GetMapping("/visits")
  public ResponseEntity<List<LogVisitResponse>> getVisitForShortUrl() {
    List<LogVisitResponse> logVisitResponseList = logVisitResponseMapper
        .toLogVisitResponse(urlLogUseCase.retrieveTimesVisitForShortUrl());

    return new ResponseEntity<>(logVisitResponseList, HttpStatus.OK);
  }
}
