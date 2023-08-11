package by.itacademy.auditservice.service.api;

import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import jakarta.validation.Valid;

public interface IAuditAccepterService {
    AuditEntity save(@Valid AuditCreateDTO item);
}
