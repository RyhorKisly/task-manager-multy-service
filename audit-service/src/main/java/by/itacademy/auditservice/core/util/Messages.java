package by.itacademy.auditservice.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Messages {
    public static final String ERROR_MESSAGE_WHEN_TIME_OUT = "The request for an external service is taking too long. " +
            "Please wait or contact your administrator";
    public static final String ERROR_MESSAGE_WHEN_CIRCUIT_BREAKER_OPEN = "CircuitBreaker activated. " +
            "It's in the stage - open. You can't do the same request for about 45 sec.";
}
