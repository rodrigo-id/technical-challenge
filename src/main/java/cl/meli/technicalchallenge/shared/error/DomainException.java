package cl.meli.technicalchallenge.shared.error;

import org.springframework.http.HttpStatus;

public class DomainException extends RuntimeException{

  private final String code;
  private final String tagService;
  private RuntimeException runtimeException;
  private final HttpStatus httpStatus;

  public DomainException(String code, String message, String tagService, HttpStatus httpStatus) {
    super(message);
    this.code = code;
    this.tagService = tagService;
    this.httpStatus = httpStatus;
  }
  public DomainException(String code, String message, String tagService, RuntimeException runtimeException,
                         HttpStatus httpStatus) {
    super(message);
    this.code = code;
    this.tagService = tagService;
    this.runtimeException = runtimeException;
    this.httpStatus = httpStatus;
  }

  public String getCode() {
    return code;
  }

  public String getTagService() {
    return tagService;
  }

  public RuntimeException getRuntimeException() {
    return runtimeException;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
