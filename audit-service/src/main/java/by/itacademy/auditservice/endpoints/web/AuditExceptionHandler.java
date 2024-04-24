package by.itacademy.auditservice.endpoints.web;

import by.itacademy.auditservice.core.exceptions.FindEntityException;
import by.itacademy.sharedresource.core.enums.ErrorType;
import by.itacademy.sharedresource.core.errors.ErrorMessage;
import by.itacademy.sharedresource.core.errors.ErrorResponse;
import by.itacademy.sharedresource.core.errors.StructuredErrorResponse;
import by.itacademy.sharedresource.core.exceptions.ServiceCallNotPermittedException;
import by.itacademy.sharedresource.core.exceptions.ServiceTimeoutException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
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
import java.util.concurrent.TimeoutException;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@RestControllerAdvice
@Log4j2
public class AuditExceptionHandler {
    private static final String INCORRECT_DATA = "The request contains incorrect data. Change request and try again or contact support!";
    private static final String INCORRECT_CHARACTERS = "Incorrect characters. Change request and try it again!";
    private static final String SERVER_ERROR = "Internal server Error. Please, contact support!";

//    Если в интерфейсе сервиса проставить @Valid и неверные данные ввести в dto
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleInvalidArgument(ConstraintViolationException ex) {
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new ArrayList<>());
        response.setLogref(ErrorType.STRUCTURED_ERROR);

        ex.getConstraintViolations().forEach(violation ->
                response.getErrors().add(new ErrorMessage(
                        violation.getPropertyPath().toString(), violation.getMessage()))
        );

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //    Если в json неверные данные передать в дто в соответствии с валидацией
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new ArrayList<>());

        for (FieldError error : errors) {
            response.getErrors().add( new ErrorMessage(error.getField(), error.getDefaultMessage()));
        }

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            HttpMessageConversionException.class,
            FeignException.class
    })
    public ResponseEntity<?> handleBadRequest(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setLogref(ErrorType.ERROR);
        response.setMessage(INCORRECT_DATA);
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class,      // Если сработал constraint form db
            FindEntityException.class
    })
    public ResponseEntity<?> handleInvalidArgument(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setLogref(ErrorType.ERROR);
        response.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Если передать неверный тип данных в параметры
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentTypeMismatchException ex) {
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