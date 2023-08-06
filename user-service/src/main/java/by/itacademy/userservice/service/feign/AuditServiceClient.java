package by.itacademy.userservice.service.feign;

import by.itacademy.userservice.core.dto.AuditSendDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//TODO переместить запись урла в файл с пропертями
@FeignClient(value = "audit-service", url = "http://localhost:8081/audit")
public interface AuditServiceClient {

    @PostMapping
    ResponseEntity<AuditSendDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody AuditSendDTO auditSendDTO
            );
}
