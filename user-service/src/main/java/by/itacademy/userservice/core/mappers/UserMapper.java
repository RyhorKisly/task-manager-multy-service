package by.itacademy.userservice.core.mappers;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import org.mapstruct.Context;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "uuid", expression = "java(setUUID())")
    @Mapping(target = "password", expression = "java(encodePassword(userCreateDTO.getPassword(), encoder))")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    UserEntity userCreateDtoToUserEntity(UserCreateDTO userCreateDTO, @Context PasswordEncoder encoder);

    @Mapping(target = "uuid", expression = "java(setUUID())")
    @Mapping(target = "password", expression = "java(encodePassword(item.getPassword(), encoder))")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "role", expression = "java(setRole())")
    @Mapping(target = "status", expression = "java(setStatus())")
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    UserEntity userRegistrationDtoToUserEntity(UserRegistrationDTO item, @Context PasswordEncoder encoder);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "role", source = "role")
    UserShortDTO userDtoToUserShortDto(UserDTO userDTO);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "role", source = "role")
    UserShortDTO userEntityToUserShortDto(UserEntity userEntity);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "dtCreate", expression = "java(formatData(userEntity.getDtCreate()))")
    @Mapping(target = "dtUpdate", expression = "java(formatData(userEntity.getDtUpdate()))")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    UserDTO userEntityToUserDto(UserEntity userEntity);

    List<UserDTO> UserEntitiesToUserDTOs(List<UserEntity> userEntities);

    default UUID setUUID() {
        return UUID.randomUUID();
    }

    default String encodePassword(String password, PasswordEncoder encoder) {
        return encoder.encode(password);
    }

    default UserRole setRole() {
        return UserRole.USER;
    }
    default UserStatus setStatus() {
        return UserStatus.WAITING_ACTIVATION;
    }

    default Long formatData(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
