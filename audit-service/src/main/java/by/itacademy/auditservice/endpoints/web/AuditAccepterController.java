package by.itacademy.auditservice.endpoints.web;

import by.itacademy.auditservice.service.api.IAuditAccepterService;
import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class AuditAccepterController {
    private final IAuditAccepterService auditAccepterService;

    @PostMapping("/audit")
    public ResponseEntity<?> save(
            @RequestBody @Valid AuditCreateDTO auditCreateDTO
            ) {
        auditAccepterService.save(auditCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}