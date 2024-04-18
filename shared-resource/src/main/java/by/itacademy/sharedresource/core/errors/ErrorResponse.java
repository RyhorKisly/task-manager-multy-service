package by.itacademy.sharedresource.core.errors;

import by.itacademy.sharedresource.core.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private ErrorType logref;
    private String message;
}
