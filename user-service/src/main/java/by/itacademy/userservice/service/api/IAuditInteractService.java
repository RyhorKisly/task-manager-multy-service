package by.itacademy.userservice.service.api;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.dao.entity.UserEntity;

import java.util.UUID;

public interface IAuditInteractService {
    void send(UUID uuid, UserShortDTO userShortDTO, String text);

    void send(UserShortDTO userShortDTO, String text);

}
