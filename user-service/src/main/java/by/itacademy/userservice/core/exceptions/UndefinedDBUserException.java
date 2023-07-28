package by.itacademy.userservice.core.exceptions;

import org.springframework.dao.DataAccessException;

public class UndefinedDBUserException extends DataAccessException {
    public UndefinedDBUserException(String msg) {
        super(msg);
    }

    public UndefinedDBUserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
