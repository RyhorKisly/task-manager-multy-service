package by.itacademy.auditservice.dao.repositories;

import by.itacademy.auditservice.dao.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IAuditDao extends JpaRepository<AuditEntity, UUID> {
    @Override
    Page<AuditEntity> findAll(Pageable pageable);
    @Override
    Optional<AuditEntity> findById(UUID uuid);
}
