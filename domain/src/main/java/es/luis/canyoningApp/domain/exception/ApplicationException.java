package es.luis.canyoningApp.domain.exception;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {

  private static final String defaultErrorMessage = "An error has occurred.";

  private final String errorCode;

  private final int httpCode;

  public ApplicationException(String message, int httpCode) {
    super(message, new Throwable(defaultErrorMessage));
    this.errorCode = String.valueOf(httpCode);
    this.httpCode = httpCode;
  }

  public ApplicationException(String message, Throwable cause, int httpCode) {
    super(message, cause);
    this.errorCode = String.valueOf(httpCode);
    this.httpCode = httpCode;
  }

  public ApplicationException(String message, String errorCode, int httpCode) {
    super(message);
    this.errorCode = errorCode;
    this.httpCode = httpCode;
  }

  public ApplicationException(String message, String errorCode, Throwable cause, int httpCode) {
    super(message, cause);
    this.errorCode = errorCode;
    this.httpCode = httpCode;
  }
}
