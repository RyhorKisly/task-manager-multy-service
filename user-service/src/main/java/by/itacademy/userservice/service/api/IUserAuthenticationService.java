package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.dao.entity.UserEntity;

public interface IUserAuthenticationService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);
    void authorizeUser(UserLoginDTO userLoginDTO);
}
