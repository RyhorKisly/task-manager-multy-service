package by.itacademy.auditservice.core.exceptions;

import org.springframework.dao.DataAccessException;

import java.io.Serial;

public class FindEntityException extends DataAccessException {
    @Serial
    private static final long serialVersionUID = 5468582777157196812L;

    public FindEntityException(String message) {
        super(message);
    }

    public FindEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
