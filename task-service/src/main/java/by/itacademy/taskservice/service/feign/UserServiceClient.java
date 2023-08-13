package by.itacademy.taskservice.service.feign;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO переместить запись урла в файл с пропертями
@FeignClient(value = "user-service", url = "${app.user-url}")
public interface UserServiceClient {

    @GetMapping("/me")
    ResponseEntity<UserShortDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken
            );

    @PostMapping("/validation")
    void check(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody List<UUID> uuids
    );

}
