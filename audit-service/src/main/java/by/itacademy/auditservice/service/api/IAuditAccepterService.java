package by.itacademy.auditservice.service.api;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import jakarta.validation.Valid;

public interface IAuditAccepterService {
    AuditDTO save(@Valid AuditCreateDTO item);
}
