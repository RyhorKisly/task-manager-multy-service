package by.itacademy.userservice.core.converters;

import by.itacademy.userservice.core.dto.UserDTO;
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
        userDTO.setRole(entity.getRole());
        userDTO.setStatus(entity.getStatus());
        return userDTO;
    }

}
