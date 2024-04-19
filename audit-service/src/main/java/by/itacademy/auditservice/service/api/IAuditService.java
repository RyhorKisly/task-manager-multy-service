package by.itacademy.auditservice.service.api;

import by.itacademy.auditservice.core.dto.AuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface IAuditService {
    Page<AuditDTO> get(PageRequest pageRequest);
    AuditDTO get(UUID uuid);
}
