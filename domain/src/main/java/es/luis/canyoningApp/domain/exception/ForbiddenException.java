package es.luis.canyoningApp.domain.exception;

public class ForbiddenException extends ApplicationException {

    private static final String defaultErrorMessage = "User is not authorized for this operation.";

    private static final int HTTP_CODE = 403;

    public ForbiddenException() {
        super(defaultErrorMessage, HTTP_CODE);
    }

    public ForbiddenException(String message) {
        super(message, new Throwable(defaultErrorMessage), HTTP_CODE);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, HTTP_CODE);
    }

    public ForbiddenException(String message, String errorCode) {
        super(message, errorCode, HTTP_CODE);
    }

    public ForbiddenException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause, HTTP_CODE);
    }
}
