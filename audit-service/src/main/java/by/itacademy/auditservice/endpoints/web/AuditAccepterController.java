package by.itacademy.auditservice.endpoints.web;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.endpoints.web.api.AuditAccepterControllerApi;
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
public class AuditAccepterController implements AuditAccepterControllerApi {
    private final IAuditAccepterService auditAccepterService;

    @Override
    public ResponseEntity<AuditDTO> save(AuditCreateDTO auditCreateDTO) {
        AuditDTO auditDTO = auditAccepterService.save(auditCreateDTO);
        return new ResponseEntity<>(auditDTO, HttpStatus.CREATED);
    }
}