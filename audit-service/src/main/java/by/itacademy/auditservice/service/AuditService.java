package by.itacademy.auditservice.service;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.exceptions.FindEntityException;
import by.itacademy.auditservice.core.mappers.AuditMapper;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.dao.repositories.IAuditDao;
import by.itacademy.auditservice.service.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {
    private static final String ERROR_GET_RESPONSE = "Failed to get audit. Try again or contact support!";
    private static final String USER_NOT_EXIST_RESPONSE = "Audit with this id does not exist!";
    private final IAuditDao IAuditDao;
    private final AuditMapper auditMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<AuditDTO> get(PageRequest pageRequest) {
        try {
            Page<AuditEntity> auditEntities =  IAuditDao.findAll(pageRequest);
            return auditEntities.map(auditMapper::auditEntityToAuditDTO);
        } catch (DataAccessException ex) {
            throw new FindEntityException(ERROR_GET_RESPONSE, ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AuditDTO get(UUID uuid) {
        AuditEntity auditEntity = IAuditDao.findById(uuid)
                .orElseThrow(() -> new FindEntityException(USER_NOT_EXIST_RESPONSE));
        return auditMapper.auditEntityToAuditDTO(auditEntity);
    }

}



