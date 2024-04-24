package by.itacademy.userservice.service.feign;

import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import by.itacademy.userservice.service.feign.factories.AuditClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Feign client for communicating with the Audit service.
 */
@FeignClient(name = "${app.audit-name}", fallbackFactory = AuditClientFallBackFactory.class)
public interface AuditServiceClient {


    /**
     * Sends an audit request with the provided bearer token and audit creation DTO.
     *
     * @param bearerToken The bearer token used for authentication.
     * @param auditCreateDTO The audit creation DTO containing audit information.
     * @return The response entity containing the result of the audit request.
     */
    @PostMapping("/audit")
    ResponseEntity<AuditCreateDTO> send(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody AuditCreateDTO auditCreateDTO
            );
}