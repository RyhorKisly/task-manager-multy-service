package by.itacademy.userservice.service;

import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.enums.UserRole;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.service.api.IUserAuthenticationService;
import by.itacademy.userservice.service.api.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {
    private final IUserService userService;

    public UserAuthenticationService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setMail(userRegistrationDTO.getMail());
        userCreateDTO.setFio(userRegistrationDTO.getFio());
        userCreateDTO.setPassword(userRegistrationDTO.getPassword());
        userCreateDTO.setRole(UserRole.USER);
        userCreateDTO.setStatus(UserStatus.WAITING_ACTIVATION);

        userService.save(userCreateDTO);

//      TODO нужно отправить письмо на почту этого юзера для подтверждения регистрации
    }

    @Override
    public void authorizeUser(UserLoginDTO userLoginDTO) {
        userService.get(userLoginDTO.getMail(), userLoginDTO.getPassword());
    }

}
