package by.itacademy.taskservice.service.feign;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.taskservice.service.feign.factories.UserClientFallBackFactory;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Feign client for communicating with the user service.
 */
@FeignClient(value = "user-service", fallbackFactory = UserClientFallBackFactory.class)
public interface UserServiceClient {

    /**
     * Sends a request to retrieve user information for the authenticated user.
     *
     * @param bearerToken The bearer token used for authentication.
     * @return The response entity containing user information.
     */
    @GetMapping("/users/me")
    ResponseEntity<UserShortDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken
            );

    /**
     * Checks the validation of users with the provided UUIDs.
     *
     * @param bearerToken The bearer token used for authentication.
     * @param uuids The list of UUIDs to check validation for.
     */
    @PostMapping("/users/validation")
    void check(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody List<UUID> uuids
    );

}
