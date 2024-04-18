package by.itacademy.taskservice.core.exceptions;

import org.springframework.dao.DataAccessException;

import java.io.Serial;

public class UndefinedDBEntityException extends DataAccessException {
    @Serial
    private static final long serialVersionUID = -7038847169682929770L;

    public UndefinedDBEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
