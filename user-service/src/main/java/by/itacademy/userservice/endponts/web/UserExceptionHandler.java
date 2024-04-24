package by.itacademy.userservice.endponts.web;

import by.itacademy.sharedresource.core.enums.ErrorType;
import by.itacademy.sharedresource.core.errors.ErrorMessage;
import by.itacademy.sharedresource.core.errors.ErrorResponse;
import by.itacademy.sharedresource.core.errors.StructuredErrorResponse;
import by.itacademy.sharedresource.core.exceptions.NotActivatedException;
import by.itacademy.sharedresource.core.exceptions.NotVerifiedCoordinatesException;
import by.itacademy.sharedresource.core.exceptions.ServiceCallNotPermittedException;
import by.itacademy.sharedresource.core.exceptions.ServiceTimeoutException;
import by.itacademy.sharedresource.core.exceptions.VerificationException;
import by.itacademy.userservice.core.exceptions.FindEntityException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static by.itacademy.userservice.core.util.Messages.INCORRECT_CHARACTERS;
import static by.itacademy.userservice.core.util.Messages.SERVER_ERROR;

@RestControllerAdvice
@Log4j2
public class UserExceptionHandler {

//    Если в интерфейсе сервиса проставить @Valid и неверные данные ввести в dto
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(ConstraintViolationException ex) {
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new ArrayList<>());
        response.setLogref(ErrorType.STRUCTURED_ERROR);

        ex.getConstraintViolations().forEach(violation ->
                response.getErrors().add(
                        new ErrorMessage(violation.getPropertyPath().toString(), violation.getMessage()))
        );

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //    Если в json неверные данные передать в дто в соответствии с валидацией
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new ArrayList<>());

        for (FieldError error : errors) {
            response.getErrors().add( new ErrorMessage(error.getField(), error.getDefaultMessage()));
        }

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            HttpMessageConversionException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setLogref(ErrorType.ERROR);
        response.setMessage("incorrect.data");
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class,      // Если сработал constraint form db
            FindEntityException.class,
            NotActivatedException.class,
            IllegalArgumentException.class,
            VerificationException.class,
            NotVerifiedCoordinatesException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidArgument(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setLogref(ErrorType.ERROR);
        response.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Если передать неверный тип данных в параметры
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleInvalidArgument(MethodArgumentTypeMismatchException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setLogref(ErrorType.ERROR);
        response.setMessage(INCORRECT_CHARACTERS);
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle exceptions working with feign when:
     * thrown TimeoutException when we wait too long response;
     * thrown CallNotPermittedException when CircuitBreaker changed to open.
     * @param ex with our message
     * @return {@link ResponseEntity} with {@link ErrorResponse}
     */
    @ExceptionHandler({
            ServiceTimeoutException.class,
            ServiceCallNotPermittedException.class
    })
    public ResponseEntity<ErrorResponse> handleInnerError(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setLogref(ErrorType.ERROR);
        response.setMessage(ex.getMessage());

        log.error(ex.getMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<?> handleInnerError(Exception ex) {
        ErrorResponse response = new ErrorResponse(
                ErrorType.ERROR,
                SERVER_ERROR
        );
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}