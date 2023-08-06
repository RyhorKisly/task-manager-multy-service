package by.itacademy.userservice.core.exceptions;

public class NotActivatedException extends RuntimeException{
    public NotActivatedException() {
    }

    public NotActivatedException(String message) {
        super(message);
    }

    public NotActivatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
