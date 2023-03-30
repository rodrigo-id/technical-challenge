package cl.meli.technicalchallenge.infraestructure.httpclient.handler;

import cl.meli.technicalchallenge.infraestructure.httpclient.models.NotificationError;
import cl.meli.technicalchallenge.shared.error.DomainException;
import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<NotificationError> handlerException(DomainException domainException) {

    NotificationError notificationError = new NotificationError();
    notificationError.setStatus(domainException.getHttpStatus().getReasonPhrase());
    notificationError.setCategory("Domain error");
    notificationError.setCode(domainException.getCode());
    notificationError.setFieldName(domainException.getTagService());
    notificationError.setMessage(domainException.getMessage());
    notificationError.setTimestamp(OffsetDateTime.now());
    notificationError.setDescription(
        domainException.getException() == null ? "" :
        domainException.getException().getMessage());

    return new ResponseEntity<>(notificationError, domainException.getHttpStatus());
  }
}
