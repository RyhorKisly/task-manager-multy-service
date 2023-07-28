package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserEntity save(@Valid UserCreateDTO userCreateDTO);
    Page<UserEntity> get(PageRequest pageRequest);
    UserEntity get(UUID uuid);
    void get(String mail, String password);
    void update(@Valid UserCreateDTO userCreateDTO, CoordinatesDTO coordinatesDTO);


}
