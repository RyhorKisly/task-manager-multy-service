package by.itacademy.auditservice.service;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.exceptions.FindEntityException;
import by.itacademy.auditservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.auditservice.dao.entity.UserEntity;
import by.itacademy.auditservice.dao.repositories.IAuditDao;
import by.itacademy.auditservice.service.api.IAuditService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class AuditService implements IAuditService {
    private static final String ERROR_GET_RESPONSE = "Failed to get user(s). Try again or contact support!";
    private static final String USER_NOT_EXIST_RESPONSE = "User with this id does not exist!";
    private final IAuditDao IAuditDao;

    public AuditService(IAuditDao IAuditDao) {
        this.IAuditDao = IAuditDao;
    }

    @Override
    public Page<AuditEntity> get(PageRequest pageRequest) {
        try {
            return IAuditDao.findAll(pageRequest);
        } catch (DataAccessException ex) {
            throw new FindEntityException(ERROR_GET_RESPONSE, ex);
        }
    }

    @Override
    public AuditEntity get(UUID uuid) {
        return IAuditDao.findById(uuid)
                .orElseThrow(() -> new FindEntityException(USER_NOT_EXIST_RESPONSE));
    }

    @Override
    public void save(AuditDTO item) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID());
        userEntity.setMail(item.getUser().getMail());
        userEntity.setFio(item.getUser().getFio());
        userEntity.setRole(item.getUser().getRole().name());

        AuditEntity entity = new AuditEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setUser(userEntity);
        entity.setText(item.getText());
        entity.setType(item.getType().name());
        entity.setId(item.getId());

        try{
            IAuditDao.save(entity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }
    }
}



