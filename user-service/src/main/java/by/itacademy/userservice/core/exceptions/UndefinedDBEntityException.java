package by.itacademy.userservice.core.exceptions;

import org.springframework.dao.DataAccessException;

import java.io.Serial;

public class UndefinedDBEntityException extends DataAccessException {
    @Serial
    private static final long serialVersionUID = 3750428995950490204L;

    public UndefinedDBEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
