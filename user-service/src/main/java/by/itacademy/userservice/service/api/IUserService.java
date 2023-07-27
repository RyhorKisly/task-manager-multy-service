package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    Page<UserEntity> get(PageRequest pageRequest);
    UserEntity get(UUID uuid);
    UserEntity save(@Valid UserCreateDTO userCreateDTO);
    void update(@Valid UserCreateDTO userCreateDTO, CoordinatesDTO coordinatesDTO);


}
