package by.itacademy.auditservice.service.feign;

import by.itacademy.auditservice.service.feign.factories.UserClientFallBackFactory;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * Feign client for communicating with the user service.
 */
@FeignClient(value = "user-service", fallbackFactory = UserClientFallBackFactory.class)
public interface UserServiceClient {

    /**
     * Retrieves user information for the current user.
     *
     * @param bearerToken The bearer token used for authentication.
     * @return The response entity containing the user information.
     */
    @GetMapping("/users/me")
    ResponseEntity<UserShortDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken
            );
}
