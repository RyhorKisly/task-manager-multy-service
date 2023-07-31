package by.itacademy.auditservice.controllers;

import by.itacademy.auditservice.core.dto.AuditCreateDTO;
import by.itacademy.auditservice.service.api.IAuditAccepterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping()
public class AuditAccepterController {
    private final IAuditAccepterService auditAccepterService;

    public AuditAccepterController(
            IAuditAccepterService auditAccepterService
    ) {
        this.auditAccepterService = auditAccepterService;
    }

    @PostMapping("/audit")
    public ResponseEntity<?> save(
            @RequestBody @Valid AuditCreateDTO auditCreateDTO
            ) {
        auditAccepterService.save(auditCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}