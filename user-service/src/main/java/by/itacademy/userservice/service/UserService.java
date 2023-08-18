package by.itacademy.userservice.service;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.sharedresource.core.exceptions.NotActivatedException;
import by.itacademy.sharedresource.core.exceptions.NotVerifiedCoordinatesException;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.repositories.IUserDao;
import by.itacademy.userservice.service.api.IAuditInteractService;
import by.itacademy.userservice.service.api.IUserService;
import by.itacademy.userservice.core.exceptions.FindEntityException;
import by.itacademy.userservice.core.exceptions.UndefinedDBEntityException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
public class UserService implements IUserService {
    private static final String ERROR_UPDATE_RESPONSE = "Failed to update user. Wrong coordinates!";
    private static final String ERROR_GET_RESPONSE = "Failed to get user(s). Try again or contact support!";
    private static final String USER_NOT_EXIST_RESPONSE = "User with this id does not exist!";
    private static final String USER_EXIST_RESPONSE = "User with this login exists";
    private static final String NAME_MAIL_CONSTRAINT = "users_mail_unique";
    private static final String WRONG_MAIL_RESPONSE = "Wrong mail";
    private static final String USER_SAVED = "User: %s was created";
    private static final String USER_UPDATED = "User: %s was updated";
    private static final String NOT_FOUND_SOME_USERS = "There are non-existent users in the query";
    private static final String NOT_VERIFIED_RESPONSE = "User: %s, is not activated. " +
            "To activate, follow the link sent to the email specified during registration. " +
            "If you didn't receive a link, please contact your administrator.";
    private final IUserDao userDao;
    private final PasswordEncoder encoder;
    private final IAuditInteractService auditInteractService;
    public UserService(
            IUserDao userDao,
            PasswordEncoder encoder,
            IAuditInteractService auditInteractService
    ) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.auditInteractService = auditInteractService;
    }

    @Override
    @Transactional
    public UserEntity createByUser(UserCreateDTO item) {
        UserEntity userEntity = convertDTOToEntity(item);
        userEntity = checkAndSaveUserEntity(userEntity);

        String text =  String.format(USER_SAVED, userEntity.getMail());
        auditInteractService.send(userEntity, text);

        return userEntity;
    }

    @Override
    @Transactional
    public UserEntity createWithRegistration(UserRegistrationDTO item) {
        UserEntity userEntity = ConvertDTOToEntity(item);

        return checkAndSaveUserEntity(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> get(PageRequest pageRequest) {
        try {
            return userDao.findAll(pageRequest);
        } catch (DataAccessException ex) {
            throw new FindEntityException(ERROR_GET_RESPONSE, ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity get(UUID uuid) {
            return userDao.findById(uuid)
                    .orElseThrow(() -> new FindEntityException(USER_NOT_EXIST_RESPONSE));
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity get(String mail) {
        return userDao.findByMail(mail)
                .orElseThrow(() -> new FindEntityException(WRONG_MAIL_RESPONSE));
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity get(String mail, UserStatus status) {
        return userDao.findByMailAndStatus(mail, status)
                .orElseThrow(() -> new NotActivatedException(String.format(NOT_VERIFIED_RESPONSE, mail)));
    }

    @Override
    @Transactional
    public void update(UserCreateDTO item, CoordinatesDTO coordinates) {

        UserEntity userEntity = userDao.findById(coordinates.getUuid())
                .orElseThrow(() -> new FindEntityException(USER_NOT_EXIST_RESPONSE));

        if(!userEntity.getDtUpdate().withNano(0)
                .isEqual(coordinates.getDtUpdate().withNano(0))
        ) {
            throw new NotVerifiedCoordinatesException(ERROR_UPDATE_RESPONSE);
        }

        setFieldsToUpdate(userEntity, item);

        try {
            userDao.saveAndFlush(userEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }

        String text =  String.format(USER_UPDATED, userEntity.getMail());
        auditInteractService.send(userEntity, text);
    }

    @Override
    @Transactional
    public void activate(UserEntity userEntity) {
        userEntity.setStatus(UserStatus.ACTIVATED);
        try {
            userDao.save(userEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> validate(List<UUID> uuids) {
        List<UserEntity> entities = userDao.findAllById(uuids);
        validate(entities, uuids);
        return entities;
    }

    private UserEntity convertDTOToEntity(UserCreateDTO item) {
        UserEntity entity = new UserEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setMail(item.getMail());
        entity.setFio(item.getFio());
        entity.setRole(item.getRole());
        entity.setStatus(item.getStatus());
        entity.setPassword(encoder.encode(item.getPassword()));
        return entity;
    }

    private UserEntity ConvertDTOToEntity(UserRegistrationDTO item) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID());
        userEntity.setMail(item.getMail());
        userEntity.setFio(item.getFio());
        userEntity.setRole(UserRole.USER);
        userEntity.setStatus(UserStatus.WAITING_ACTIVATION);
        userEntity.setPassword(encoder.encode(item.getPassword()));
        return userEntity;
    }

    private void setFieldsToUpdate(UserEntity userEntity, UserCreateDTO item) {
        userEntity.setMail(item.getMail());
        userEntity.setFio(item.getFio());
        userEntity.setRole(item.getRole());
        userEntity.setStatus(item.getStatus());
        userEntity.setPassword(encoder.encode(item.getPassword()));
    }

    private UserEntity checkAndSaveUserEntity(UserEntity userEntity) {
        try {
            userEntity = userDao.saveAndFlush(userEntity);
        } catch (DataAccessException ex) {
            if (ex.getMessage().contains(NAME_MAIL_CONSTRAINT)) {
                throw new DataIntegrityViolationException(USER_EXIST_RESPONSE, ex);
            } else {
                throw new UndefinedDBEntityException(ex.getMessage(), ex);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException (ex.getMessage(), ex);
        }
        return userEntity;
    }

    private void validate(List<UserEntity> entities, List<UUID> uuids) {
        if(entities.size() != uuids.size()) {
            throw new IllegalArgumentException(NOT_FOUND_SOME_USERS);
        }
        for (UserEntity entity : entities) {
            if(!entity.getStatus().equals(UserStatus.ACTIVATED)) {
                throw new NotActivatedException(String.format(NOT_VERIFIED_RESPONSE, entity.getUuid()));
            }
        }
    }

}
