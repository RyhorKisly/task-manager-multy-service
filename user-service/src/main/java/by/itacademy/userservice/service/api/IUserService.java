package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;
@Validated
public interface IUserService {
    UserEntity createByUser(@Valid UserCreateDTO userCreateDTO);
    UserEntity createWithRegistration(@Valid UserRegistrationDTO userRegistrationDTO);
    Page<UserEntity> get(PageRequest pageRequest);
    UserEntity get(UUID uuid);
    UserEntity get(String mail);
    void update(@Valid UserCreateDTO userCreateDTO, CoordinatesDTO coordinatesDTO);
    void activate(@Valid UserEntity userEntity);


}
