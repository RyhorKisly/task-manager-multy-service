package by.itacademy.auditservice.core.exceptions;

import org.springframework.dao.DataAccessException;

import java.io.Serial;

public class UndefinedDBEntityException extends DataAccessException {
    @Serial
    private static final long serialVersionUID = 9010038617573756389L;

    public UndefinedDBEntityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
