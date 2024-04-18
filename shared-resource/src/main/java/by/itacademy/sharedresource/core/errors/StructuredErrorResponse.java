package by.itacademy.sharedresource.core.errors;

import by.itacademy.sharedresource.core.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructuredErrorResponse {
    private ErrorType logref;
    private List<ErrorMessage> errors;
}
