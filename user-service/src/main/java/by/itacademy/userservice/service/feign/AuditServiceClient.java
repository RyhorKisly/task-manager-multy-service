package by.itacademy.userservice.service.feign;

import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//TODO переместить запись урла в файл с пропертями
@FeignClient(value = "audit-service", url = "${app.audit-url}")
public interface AuditServiceClient {

    @PostMapping
    ResponseEntity<AuditCreateDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody AuditCreateDTO auditCreateDTO
            );
}
