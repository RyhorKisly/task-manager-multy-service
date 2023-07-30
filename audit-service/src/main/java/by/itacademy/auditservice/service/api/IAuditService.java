package by.itacademy.auditservice.service.api;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface IAuditService {
    Page<AuditEntity> get(PageRequest pageRequest);
    AuditEntity get(UUID uuid);
    void save(AuditDTO auditDTO);
}
