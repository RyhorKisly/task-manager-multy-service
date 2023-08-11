package by.itacademy.auditservice.core.exceptions;

import org.springframework.dao.DataAccessException;

public class UndefinedDBEntityException extends DataAccessException {
    public UndefinedDBEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
