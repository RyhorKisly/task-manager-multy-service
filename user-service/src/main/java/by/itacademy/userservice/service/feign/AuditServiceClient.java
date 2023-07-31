package by.itacademy.userservice.service.feign;

import by.itacademy.userservice.core.dto.UserSendDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "audit-service", url = "http://localhost:8081/audit")
public interface AuditServiceClient {

    @PostMapping
    ResponseEntity<?> sendAudit(
            @RequestBody UserSendDTO userSendDTO
            );
}
