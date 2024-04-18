package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import jakarta.validation.Valid;

public interface IUserAuthenticationService {

    void register(@Valid UserRegistrationDTO userRegistrationDTO);
    void verify(String code, String mail);
    String authorize(@Valid UserLoginDTO userLoginDTO);
    UserDTO getUser();
}
