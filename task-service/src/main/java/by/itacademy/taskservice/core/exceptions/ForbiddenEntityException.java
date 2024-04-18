package by.itacademy.taskservice.core.exceptions;

import java.io.Serial;

public class ForbiddenEntityException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8516991130009678687L;

    public ForbiddenEntityException(String message) {
        super(message);
    }

}
