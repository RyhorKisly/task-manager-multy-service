package by.itacademy.sharedresource.core.exceptions;

import java.io.Serial;

public class ServiceCallNotPermittedException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -7087193314393413504L;

    public ServiceCallNotPermittedException(String message, Throwable cause) {
        super(message, cause);
    }
}
