package by.itacademy.taskservice.core.exceptions;

import org.springframework.dao.DataAccessException;

import java.io.Serial;

public class FindEntityException extends DataAccessException {
    @Serial
    private static final long serialVersionUID = 5044721351368647275L;

    public FindEntityException(String message) {
        super(message);
    }

    public FindEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
