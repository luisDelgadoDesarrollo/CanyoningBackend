package es.luis.canyoningApp.application.rest.error;

import es.luis.canyoningApp.canyoningApp.application.rest.model.ErrorDto;
import es.luis.canyoningApp.domain.exception.ApplicationException;
import java.util.NoSuchElementException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ErrorDto> handleGenericException(ApplicationException exception) {
    logger.error(exception.getClass().getName() + ": ", exception);
    return buildErrorResponseEntity(
        HttpStatus.valueOf(exception.getHttpCode()),
        exception.getErrorCode(),
        exception.getMessage(),
        exception.getCause());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDto> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    logger.error("IllegalArgumentException: ", exception);
    return buildErrorResponseEntity(
        HttpStatus.BAD_REQUEST,
        "400",
        "Bad Request. Check the parameters or body of the request.",
        exception);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorDto> handleNoSuchElementException(NoSuchElementException exception) {
    logger.error("NoSuchElementException: ", exception);
    return buildErrorResponseEntity(
        HttpStatus.NOT_FOUND, "404", "The resource requested not exists.", exception);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception exception) {
    logger.error("Exception: ", exception);
    return buildErrorResponseEntity(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "500",
        exception.getClass().getSimpleName() + ": " + exception.getLocalizedMessage(),
        exception.getCause());
  }

  private ResponseEntity<ErrorDto> buildErrorResponseEntity(
      HttpStatus httpStatus, String errorCode, String description, Throwable cause) {
    return ResponseEntity.status(httpStatus)
        .body(
            new ErrorDto(
                errorCode, description, cause != null ? cause.getLocalizedMessage() : null));
  }

  @Override
  protected ResponseEntity<Object> createResponseEntity(
      @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    if (body instanceof ProblemDetail problemDetail) {
      return new ResponseEntity<>(
          new ErrorDto(
              String.valueOf(statusCode.value()),
              problemDetail.getTitle(),
              problemDetail.getDetail()),
          headers,
          statusCode);
    } else {
      return super.createResponseEntity(body, headers, statusCode, request);
    }
  }
}
