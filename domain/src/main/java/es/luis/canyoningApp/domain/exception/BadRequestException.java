package es.luis.canyoningApp.domain.exception;

public class BadRequestException extends ApplicationException {

    private static final String defaultErrorMessage =
            "Bad Request. Check the parameters or body of the request.";

    private static final int HTTP_CODE = 400;

    public BadRequestException() {
        super(defaultErrorMessage, HTTP_CODE);
    }

    public BadRequestException(String message) {
        super(message, new Throwable(defaultErrorMessage), HTTP_CODE);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, HTTP_CODE);
    }

    public BadRequestException(String message, String errorCode) {
        super(message, errorCode, HTTP_CODE);
    }

    public BadRequestException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause, HTTP_CODE);
    }
}
