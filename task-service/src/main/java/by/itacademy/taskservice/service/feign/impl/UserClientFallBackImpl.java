package by.itacademy.taskservice.service.feign.impl;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.exceptions.ServiceCallNotPermittedException;
import by.itacademy.sharedresource.core.exceptions.ServiceTimeoutException;
import by.itacademy.taskservice.service.feign.UserServiceClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static by.itacademy.taskservice.core.util.Messages.ERROR_MESSAGE_WHEN_CIRCUIT_BREAKER_OPEN;
import static by.itacademy.taskservice.core.util.Messages.ERROR_MESSAGE_WHEN_TIME_OUT;

/**
 * Implementation of the AuditServiceClient interface used as a fallback mechanism.
 * This implementation handles situations when the primary service is unavailable or fails to respond.
 */
@Log4j2
public class UserClientFallBackImpl implements UserServiceClient {

    /**
     * The cause of the fallback, typically an exception indicating the reason for the fallback.
     */
    private final Throwable cause;

    /**
     * Constructs a new AuditClientFallBackImpl object with the specified cause.
     *
     * @param cause The cause of the fallback.
     */
    public UserClientFallBackImpl(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseEntity<UserShortDTO> send(String bearerToken) {
        if (cause instanceof TimeoutException) {
            log.error("The request for an external service is taking too long. TimeoutException occurred");
            throw new ServiceTimeoutException(ERROR_MESSAGE_WHEN_TIME_OUT, cause);
        } else if (cause instanceof CallNotPermittedException) {
            log.error("CircuitBreaker activated. It's in the stage - open. CallNotPermittedException occurred");
            throw new ServiceCallNotPermittedException(ERROR_MESSAGE_WHEN_CIRCUIT_BREAKER_OPEN, cause);
        }
        log.error("Failed request to external server.");
        throw new RuntimeException(cause);
    }

    @Override
    public void check(String bearerToken, List<UUID> uuids) {
        if (cause instanceof TimeoutException) {
            log.error("CircuitBreaker activated. It's in the stage - open. CallNotPermittedException occurred");
            throw new ServiceTimeoutException(ERROR_MESSAGE_WHEN_TIME_OUT, cause);
        } else if (cause instanceof CallNotPermittedException) {
            log.error("CircuitBreaker activated. It's in the stage - open. CallNotPermittedException occurred");
            throw new ServiceCallNotPermittedException(ERROR_MESSAGE_WHEN_CIRCUIT_BREAKER_OPEN, cause);
        }
        log.error("Failed request to external server.");
        throw new RuntimeException(cause);
    }
}
