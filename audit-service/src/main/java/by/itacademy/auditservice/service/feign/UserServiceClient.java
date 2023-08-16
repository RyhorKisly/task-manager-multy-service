package by.itacademy.auditservice.service.feign;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service", url = "${app.user-url}")
public interface UserServiceClient {

    @GetMapping("/me")
    ResponseEntity<UserShortDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken
            );
}
