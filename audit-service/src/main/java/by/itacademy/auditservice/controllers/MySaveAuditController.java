package by.itacademy.auditservice.controllers;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.dto.PageDTO;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.service.api.IAuditService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/adminAudit")
public class MySaveAuditController {
    private final IAuditService auditService;


    public MySaveAuditController(
            IAuditService auditService
    ) {
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid AuditDTO auditDTO
    ) {
        auditService.save(auditDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}