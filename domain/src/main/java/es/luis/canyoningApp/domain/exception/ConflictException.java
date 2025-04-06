package es.luis.canyoningApp.domain.exception;

public class ConflictException extends ApplicationException {

    private static final String defaultErrorMessage =
            "The operation cannot be made because a conflict with the current state of the target resource.";

    private static final int HTTP_CODE = 409;

    public ConflictException() {
        super(defaultErrorMessage, HTTP_CODE);
    }

    public ConflictException(String message) {
        super(message, new Throwable(defaultErrorMessage), HTTP_CODE);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause, HTTP_CODE);
    }

    public ConflictException(String message, String errorCode) {
        super(message, errorCode, HTTP_CODE);
    }

    public ConflictException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause, HTTP_CODE);
    }
}
