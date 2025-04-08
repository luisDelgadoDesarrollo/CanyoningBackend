package es.luis.canyoningApp.domain.exception;

public class NotFoundException extends ApplicationException {

  private static final String defaultErrorMessage = "The resource requested not exists.";

  private static final int HTTP_CODE = 404;

  public NotFoundException() {
    super(defaultErrorMessage, HTTP_CODE);
  }

  public NotFoundException(String message) {
    super(message, new Throwable(defaultErrorMessage), HTTP_CODE);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause, HTTP_CODE);
  }

  public NotFoundException(String message, String errorCode) {
    super(message, errorCode, HTTP_CODE);
  }

  public NotFoundException(String message, String errorCode, Throwable cause) {
    super(message, errorCode, cause, HTTP_CODE);
  }
}
