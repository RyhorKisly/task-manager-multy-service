package by.itacademy.taskservice.core.errors;

import by.itacademy.taskservice.core.enums.ErrorType;

import java.util.List;

public class StructuredErrorResponse {
    private ErrorType logref;
    private List<ErrorMessage> errors;

    public StructuredErrorResponse(ErrorType logref, List<ErrorMessage> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public StructuredErrorResponse() {
    }

    public ErrorType getLogref() {
        return logref;
    }

    public void setLogref(ErrorType logref) {
        this.logref = logref;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }
}
