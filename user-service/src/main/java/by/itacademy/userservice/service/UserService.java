package by.itacademy.userservice.service;

import by.itacademy.userservice.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.repositories.IUserDao;
import by.itacademy.userservice.service.api.IUserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
@Service
@Validated
public class UserService implements IUserService {
    private static final String ERROR_UPDATE_RESPONSE = "Failed to update user. Try again or contact support!";
    private static final String ERROR_CREATE_RESPONSE = "Failed to create user. Try again or contact support!";
    private static final String ERROR_GET_RESPONSE = "Failed to get user(s). Try again or contact support!";


    private final IUserDao userDao;
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity save(UserCreateDTO item) {
        UserEntity entity = convertUserCreateDTOToEntity(item);
        entity.setUuid(UUID.randomUUID());
        try {
            return userDao.save(entity);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ERROR_CREATE_RESPONSE, ex);
        }
    }

    @Override
    public Page<UserEntity> get(PageRequest pageRequest) {
        try {
            return userDao.findAll(pageRequest);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ERROR_GET_RESPONSE, ex);
        }
    }

    @Override
    public UserEntity get(UUID uuid) {
            return userDao.findById(uuid)
                    .orElseThrow(() -> new IllegalArgumentException(ERROR_GET_RESPONSE));
    }

    @Override
    public void update(UserCreateDTO item, CoordinatesDTO coordinates) {

        Optional<UserEntity> userEntityOpt = userDao.findById(coordinates.getUuid());
        UserEntity userEntity = userEntityOpt
                .orElseThrow(() -> new IllegalArgumentException(ERROR_GET_RESPONSE));

        if(userEntity.getDtUpdate().withNano(0)
                .isEqual(coordinates.getDtUpdate().withNano(0))
        ) {
            userEntity.setMail(item.getMail());
            userEntity.setFio(item.getFio());
            userEntity.setRole(item.getRole().toString());
            userEntity.setStatus(item.getStatus().toString());
            userEntity.setPassword(item.getPassword());

            try {
                userDao.save(userEntity);
            } catch (DataAccessException ex) {
                throw new RuntimeException(ERROR_UPDATE_RESPONSE, ex);
            }
        } else {
            throw new IllegalArgumentException(ERROR_UPDATE_RESPONSE);
        }
    }

    private UserEntity convertUserCreateDTOToEntity(@Valid UserCreateDTO item) {
        UserEntity entity = new UserEntity();
        entity.setMail(item.getMail());
        entity.setFio(item.getFio());
        entity.setRole(item.getRole().toString());
        entity.setStatus(item.getStatus().toString());
        entity.setPassword(item.getPassword());
        return entity;
    }

}
