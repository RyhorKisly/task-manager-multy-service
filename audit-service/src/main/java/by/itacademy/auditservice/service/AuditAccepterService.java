package by.itacademy.auditservice.service;

import by.itacademy.auditservice.core.dto.AuditCreateDTO;
import by.itacademy.auditservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.dao.entity.UserEntity;
import by.itacademy.auditservice.dao.repositories.IAuditDao;
import by.itacademy.auditservice.service.api.IAuditAccepterService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public AuditEntity save(AuditCreateDTO auditCreateDTO) {
        AuditEntity auditEntity = convertDTOToEntity(auditCreateDTO);
        try{
            return auditDao.save(auditEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }
    }

    private AuditEntity convertDTOToEntity(AuditCreateDTO item) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(item.getUserGeneralDTO().getUuid());
        userEntity.setMail(item.getUserGeneralDTO().getMail());
        userEntity.setFio(item.getUserGeneralDTO().getFio());
        userEntity.setRole(item.getUserGeneralDTO().getRole());

        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setUuid(UUID.randomUUID());
        auditEntity.setUser(userEntity);
        auditEntity.setText(item.getText());
        auditEntity.setType(item.getType().toUpperCase());
        auditEntity.setId(item.getId());
        return auditEntity;
    }
}
