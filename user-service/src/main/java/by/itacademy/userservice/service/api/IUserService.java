package by.itacademy.userservice.service.api;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
@Validated
public interface IUserService {
    UserDTO createByUser(@Valid UserCreateDTO userCreateDTO);
    UserDTO createWithRegistration(@Valid UserRegistrationDTO userRegistrationDTO);
    Page<UserDTO> get(PageRequest pageRequest);
    UserDTO get(UUID uuid);
    UserDTO get(String mail);
    UserEntity get(String mail, UserStatus status);
    UserDTO update(@Valid UserCreateDTO userCreateDTO, CoordinatesDTO coordinatesDTO);
    UserDTO activate(@Valid UserDTO userDTO);
    List<UserDTO> validate(List<UUID> uuids);


}
