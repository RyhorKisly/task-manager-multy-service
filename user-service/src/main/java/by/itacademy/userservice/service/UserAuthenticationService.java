package by.itacademy.userservice.service;

import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.enums.UserRole;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationToken;
import by.itacademy.userservice.service.api.IEmailService;
import by.itacademy.userservice.service.api.IUserAuthenticationService;
import by.itacademy.userservice.service.api.IUserService;
import by.itacademy.userservice.service.api.IVerificationTokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {
    private final IUserService userService;
    private final IVerificationTokenService tokenService;
    private final IEmailService emailService;

    public UserAuthenticationService(
            IUserService userService,
            IVerificationTokenService tokenService,
            IEmailService emailService
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    @Override
    public void registerUser(UserRegistrationDTO item) {
        UserEntity userEntity = createAndSaveUser(item);
        VerificationToken token = tokenService.save(createToken(userEntity));

        emailService.sendEmail(userEntity, token);
    }

    @Override
    public void verifyUser(String code, String mail) {
        UserEntity userEntity = userService.get(mail);
        VerificationToken token = tokenService.get(UUID.fromString(code));

        if(userEntity.getMail().equals(token.getUser().getMail())) {
            userEntity.setStatus(UserStatus.ACTIVATED.name());
            userService.update(userEntity);
            tokenService.delete(token.getUuid());
        } else {
            throw new RuntimeException();
        }
    }

    //TODO Доделать метод
    @Override
    public void authorizeUser(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = userService.get(userLoginDTO.getMail(), userLoginDTO.getPassword());
    }

    private UserEntity createAndSaveUser(UserRegistrationDTO item) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setMail(item.getMail());
        userCreateDTO.setFio(item.getFio());
        userCreateDTO.setPassword(item.getPassword());
        userCreateDTO.setRole(UserRole.USER);
        userCreateDTO.setStatus(UserStatus.WAITING_ACTIVATION);
        return userService.save(userCreateDTO);
    }

    private VerificationToken createToken(UserEntity item) {
        VerificationToken token = new VerificationToken();
        token.setUuid(UUID.randomUUID());
        token.setToken(UUID.randomUUID());
        token.setUser(item);
        return token;
    }

}
