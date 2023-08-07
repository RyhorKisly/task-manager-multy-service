package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.UserShortDTO;
import by.itacademy.userservice.dao.entity.UserEntity;

public interface IAuditInteractService {
    void send(UserEntity userEntity, String text);
    void send(UserEntity newEntity, UserEntity userEntity, String text);
}
