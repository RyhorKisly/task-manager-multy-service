package by.itacademy.userservice.service.api;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import jakarta.validation.Valid;

public interface IUserAuthenticationService {

    void register(@Valid UserRegistrationDTO userRegistrationDTO);
    void verify(String code, String mail);
    String authorize(@Valid UserLoginDTO userLoginDTO);
    UserEntity getUser();
    UserShortDTO getGeneralUser();
}
