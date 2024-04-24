package by.itacademy.sharedresource.core.exceptions;

import java.io.Serial;

public class ServiceTimeoutException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2163411242215116502L;

    public ServiceTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
