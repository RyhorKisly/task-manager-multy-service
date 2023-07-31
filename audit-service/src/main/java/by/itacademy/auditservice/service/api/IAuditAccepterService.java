package by.itacademy.auditservice.service.api;

import by.itacademy.auditservice.core.dto.AuditCreateDTO;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import jakarta.validation.Valid;

public interface IAuditAccepterService {
    AuditEntity save(@Valid AuditCreateDTO item);
}
