package by.itacademy.userservice.core.exceptions;

public class NotVerifiedCoordinatesException extends RuntimeException{
    public NotVerifiedCoordinatesException() {
    }

    public NotVerifiedCoordinatesException(String message) {
        super(message);
    }

    public NotVerifiedCoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }
}
