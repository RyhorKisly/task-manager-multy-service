package by.itacademy.taskservice.service.feign;

import by.itacademy.taskservice.core.dto.UserShortDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//TODO переместить запись урла в файл с пропертями
@FeignClient(value = "user-service", url = "http://localhost:8080/users/me")
public interface UserServiceClient {

    @GetMapping
    ResponseEntity<UserShortDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken
            );
}
