package cl.meli.technicalchallenge.infraestructure.httpclient.handler;

import cl.meli.technicalchallenge.infraestructure.httpclient.models.NotificationError;
import cl.meli.technicalchallenge.shared.error.DomainException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
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


  @ExceptionHandler({
      DataAccessResourceFailureException.class,
      InvalidDataAccessResourceUsageException.class,
      SQLGrammarException.class
  })
  public ResponseEntity<NotificationError> handlerJpaException(DataAccessResourceFailureException exception) {
    NotificationError notificationError = new NotificationError();
    notificationError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    notificationError.setCategory("Jpa error");
    notificationError.setCode("");
    notificationError.setFieldName("");
    notificationError.setMessage(exception.getMessage());
    notificationError.setTimestamp(OffsetDateTime.now());
    notificationError.setDescription(
        exception.getCause() == null ? "" :
            String.valueOf(exception.fillInStackTrace()));

    return new ResponseEntity<>(notificationError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
