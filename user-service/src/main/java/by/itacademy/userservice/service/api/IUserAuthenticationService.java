package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.dao.entity.VerificationToken;

import java.util.UUID;

public interface IUserAuthenticationService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);
    void verifyUser(String code, String mail);
    void authorizeUser(UserLoginDTO userLoginDTO);
}
