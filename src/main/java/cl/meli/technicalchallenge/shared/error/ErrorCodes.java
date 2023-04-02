package cl.meli.technicalchallenge.shared.error;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
  DOMAIN_INVALID_URL(
      "Error la url proporcionada no es válida - url: {0}",
      "0001",
      HttpStatus.INTERNAL_SERVER_ERROR),
  DOMAIN_SHORT_URL_NOT_FOUND(
      "Error no se encuentra la url proporcionada - url: {0}",
      "0002",
      HttpStatus.NOT_FOUND),
  DOMAIN_SHORT_URL_DELETE(
      "Error no fue posible eliminar la url proporcionada - url: {0}",
      "0003",
      HttpStatus.NOT_FOUND),
  DOMAIN_GENERATE_HASH(
      "Error problemas al generar el hash",
      "0004",
      HttpStatus.NOT_FOUND),
  DOMAIN_SHOW_LOG_INFO(
      "No se registran visitas para la url",
      "0005",
      HttpStatus.NOT_FOUND);

  private final String message;
  private final String code;
  private final HttpStatus status;

  ErrorCodes(String message, String code, HttpStatus status) {
    this.message = message;
    this.code = code;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
