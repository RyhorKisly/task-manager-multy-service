package by.itacademy.taskservice.service.feign;

import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "audit-service", url = "http://localhost:8081/audit")
public interface AuditServiceClient {

    @PostMapping
    ResponseEntity<AuditCreateDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody AuditCreateDTO auditSendDTO
            );
}
