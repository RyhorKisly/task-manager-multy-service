package by.itacademy.userservice.service;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.exceptions.NotActivatedException;
import by.itacademy.sharedresource.core.exceptions.NotVerifiedCoordinatesException;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.core.mappers.UserMapper;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.repositories.IUserDao;
import by.itacademy.userservice.service.api.IAuditInteractService;
import by.itacademy.userservice.service.api.IUserService;
import by.itacademy.userservice.core.exceptions.FindEntityException;
import by.itacademy.userservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.userservice.service.authentification.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static by.itacademy.userservice.core.util.Messages.ERROR_GET_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.ERROR_UPDATE_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.NAME_MAIL_CONSTRAINT;
import static by.itacademy.userservice.core.util.Messages.NOT_ACTIVATED_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.USER_EXIST_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.USER_NOT_EXIST_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.USER_SAVED;
import static by.itacademy.userservice.core.util.Messages.USER_UPDATED;
import static by.itacademy.userservice.core.util.Messages.WRONG_MAIL_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.NOT_FOUND_SOME_USERS;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserDao userDao;
    private final PasswordEncoder encoder;
    private final IAuditInteractService auditInteractService;
    private final UserMapper userMapper;
    private final UserHolder holder;

    @Override
    @Transactional
    public UserDTO createByUser(UserCreateDTO item) {
        UserEntity userEntity = userMapper.userCreateDtoToUserEntity(item, encoder);
        userEntity = checkAndSaveUserEntity(userEntity);

        String text =  String.format(USER_SAVED, userEntity.getMail());
        UserShortDTO userShortDTO = userMapper.userDtoToUserShortDto(holder.getUser());
        auditInteractService.send(userEntity.getUuid(), userShortDTO, text);

        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDTO createWithRegistration(UserRegistrationDTO item) {
        UserEntity userEntity = userMapper.userRegistrationDtoToUserEntity(item, encoder);
        userEntity = checkAndSaveUserEntity(userEntity);

        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> get(PageRequest pageRequest) {
        try {
            Page<UserEntity> userEntities = userDao.findAll(pageRequest);
            return userEntities.map(userMapper::userEntityToUserDto);
        } catch (DataAccessException ex) {
            throw new FindEntityException(ERROR_GET_RESPONSE, ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO get(UUID uuid) {
        UserEntity userEntity = userDao.findById(uuid)
                .orElseThrow(() -> new FindEntityException(USER_NOT_EXIST_RESPONSE));
            return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO get(String mail) {
        UserEntity userEntity = userDao.findByMail(mail)
                .orElseThrow(() -> new FindEntityException(WRONG_MAIL_RESPONSE));
        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity get(String mail, UserStatus status) {
        return userDao.findByMailAndStatus(mail, status)
                .orElseThrow(() -> new NotActivatedException(String.format(NOT_ACTIVATED_RESPONSE, mail)));
    }

    @Override
    @Transactional
    public UserDTO update(UserCreateDTO item, CoordinatesDTO coordinates) {

        UserEntity userEntity = userDao.findById(coordinates.getUuid())
                .orElseThrow(() -> new FindEntityException(USER_NOT_EXIST_RESPONSE));

        if(!userEntity.getDtUpdate().withNano(0)
                .isEqual(coordinates.getDtUpdate().withNano(0))
        ) {
            throw new NotVerifiedCoordinatesException(ERROR_UPDATE_RESPONSE);
        }

        userEntity = updateEntityFields(userEntity, item);

        try {
            userEntity = userDao.saveAndFlush(userEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }

        String text =  String.format(USER_UPDATED, userEntity.getMail());
        UserShortDTO userShortDTO = userMapper.userDtoToUserShortDto(holder.getUser());
        auditInteractService.send(userEntity.getUuid(), userShortDTO, text);
        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDTO activate(UserDTO userDTO) {
        UserEntity userEntity = userDao.findByMail(userDTO.getMail())
                .orElseThrow(() -> new FindEntityException(WRONG_MAIL_RESPONSE));
        userEntity.setStatus(UserStatus.ACTIVATED);
        try {
            userEntity = userDao.save(userEntity);
            return userMapper.userEntityToUserDto(userEntity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> validate(List<UUID> uuids) {
        List<UserEntity> entities = userDao.findAllById(uuids);
        validate(entities, uuids);
        return userMapper.UserEntitiesToUserDTOs(entities);
    }

    private UserEntity updateEntityFields(UserEntity userEntity, UserCreateDTO item) {
        userEntity.setMail(item.getMail());
        userEntity.setFio(item.getFio());
        userEntity.setRole(item.getRole());
        userEntity.setStatus(item.getStatus());
        userEntity.setPassword(encoder.encode(item.getPassword()));
        return userEntity;
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
                throw new NotActivatedException(String.format(NOT_ACTIVATED_RESPONSE, entity.getUuid()));
            }
        }
    }

}
