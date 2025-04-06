package es.luis.canyoningApp.domain.exception;

public class ExistsException extends ApplicationException {

    private static final String defaultErrorMessage =
            "Entity already exists with the same ID or combination of unique properties.";

    private static final int HTTP_CODE = 409;

    public ExistsException() {
        super(defaultErrorMessage, HTTP_CODE);
    }

    public ExistsException(String message) {
        super(message, new Throwable(defaultErrorMessage), HTTP_CODE);
    }

    public ExistsException(String message, Throwable cause) {
        super(message, cause, HTTP_CODE);
    }

    public ExistsException(String message, String errorCode) {
        super(message, errorCode, HTTP_CODE);
    }

    public ExistsException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause, HTTP_CODE);
    }
}
