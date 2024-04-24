package by.itacademy.auditservice.service;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.auditservice.core.mappers.AuditMapper;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.dao.repositories.IAuditDao;
import by.itacademy.auditservice.service.api.IAuditAccepterService;
import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class AuditAccepterService implements IAuditAccepterService {
    private final IAuditDao auditDao;
    private final AuditMapper auditMapper;
    @Override
    @Transactional
    public AuditDTO save(AuditCreateDTO auditCreateDTO) {
        AuditEntity auditEntity = auditMapper.auditCreateDTOToAuditEntity(auditCreateDTO);
        try{
            auditEntity = auditDao.saveAndFlush(auditEntity);
            return auditMapper.auditEntityToAuditDTO(auditEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }
    }
}
