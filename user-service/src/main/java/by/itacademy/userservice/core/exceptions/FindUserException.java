package by.itacademy.userservice.core.exceptions;

import org.springframework.dao.DataAccessException;

public class FindUserException extends DataAccessException {


    public FindUserException(String message) {
        super(message);
    }

    public FindUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
