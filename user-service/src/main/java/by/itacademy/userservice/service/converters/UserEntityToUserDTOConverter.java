package by.itacademy.userservice.service.converters;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.enums.UserRole;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class UserEntityToUserDTOConverter implements Converter<UserEntity, UserDTO> {
    @Override
    public UserDTO convert(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(entity.getUuid());
        userDTO.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setDtUpdate(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setMail(entity.getMail());
        userDTO.setFio(entity.getFio());
        userDTO.setRole(UserRole.valueOf(entity.getRole()));
        userDTO.setStatus(UserStatus.valueOf(entity.getStatus()));
        return userDTO;
    }

}
