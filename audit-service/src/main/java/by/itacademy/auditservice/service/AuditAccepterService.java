package by.itacademy.auditservice.service;

import by.itacademy.auditservice.core.dto.AuditCreateDTO;
import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.dto.UserDTO;
import by.itacademy.auditservice.core.enums.EssenceType;
import by.itacademy.auditservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.dao.entity.UserEntity;
import by.itacademy.auditservice.dao.repositories.IAuditDao;
import by.itacademy.auditservice.service.api.IAuditAccepterService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
public class AuditAccepterService implements IAuditAccepterService {
    private final IAuditDao auditDao;

    public AuditAccepterService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    @Override
    public AuditEntity save(AuditCreateDTO item) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(item.getUuid());
        userEntity.setMail(item.getMail());
        userEntity.setFio(item.getFio());
        userEntity.setRole(item.getRole().name());

        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setUuid(UUID.randomUUID());
        auditEntity.setUser(userEntity);
        auditEntity.setText(item.getText());
        auditEntity.setType(item.getType().toUpperCase());
        auditEntity.setId(item.getId());
        try{
            return auditDao.save(auditEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }
    }
}
