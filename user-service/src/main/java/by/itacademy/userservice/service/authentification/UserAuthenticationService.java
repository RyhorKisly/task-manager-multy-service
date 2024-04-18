package by.itacademy.userservice.service.authentification;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.exceptions.NotActivatedException;
import by.itacademy.sharedresource.core.exceptions.VerificationException;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.dto.VerificationDTO;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.core.mappers.UserMapper;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

import static by.itacademy.userservice.core.util.Messages.ERROR_VERIFY_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.NOT_VERIFIED_RESPONSE;
import static by.itacademy.userservice.core.util.Messages.USER_ACTIVATED;
import static by.itacademy.userservice.core.util.Messages.USER_REGISTERED;
import static by.itacademy.userservice.core.util.Messages.WRONG_PASSWORD_RESPONSE;

@Service
@Validated
@RequiredArgsConstructor
public class UserAuthenticationService implements IUserAuthenticationService {
    private final IUserService userService;
    private final IVerificationService verificationService;
    private final IEmailService emailService;
    private final PasswordEncoder encoder;
    private final JwtTokenHandler jwtHandler;
    private final UserHolder holder;
    private final IAuditInteractService auditInteractService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void register(UserRegistrationDTO item) {
        UserDTO userDTO = userService.createWithRegistration(item);

        VerificationDTO verificationDTO = verificationService.save(userDTO);

        emailService.sendEmail(userDTO, verificationDTO);

        UserShortDTO userShortDTO = userMapper.userDtoToUserShortDto(userDTO);
        String text = String.format(USER_REGISTERED, userDTO.getMail());
        auditInteractService.send(userDTO.getUuid(), userShortDTO, text);
    }

    @Override
    @Transactional
    public void verify(String code, String mail) {
        UserDTO userDTO;
        VerificationDTO verificationDTO;
        try {
            userDTO = userService.get(mail);
            verificationDTO = verificationService.get(mail);
        } catch (RuntimeException ex){
            throw new VerificationException(ERROR_VERIFY_RESPONSE, ex);
        }

        if(!verificationDTO.uuid().equals(UUID.fromString(code))
                && !userDTO.getStatus().equals(UserStatus.WAITING_ACTIVATION)) {
            throw new VerificationException(ERROR_VERIFY_RESPONSE);
        }

        userDTO = userService.activate(userDTO);

        verificationService.delete(verificationDTO.uuid());
        verificationService.deleteByDtCreateLessThan(LocalDateTime.now().minusDays(7));

        UserShortDTO userShortDTO = userMapper.userDtoToUserShortDto(userDTO);
        String text = String.format(USER_ACTIVATED, userDTO.getMail());
        auditInteractService.send(userShortDTO, text);
    }

    @Override
    @Transactional(readOnly = true)
    public String authorize(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = userService.get(userLoginDTO.getMail(), UserStatus.ACTIVATED);

        if(!encoder.matches(userLoginDTO.getPassword(), userEntity.getPassword())){
            throw new IllegalArgumentException(WRONG_PASSWORD_RESPONSE);
        }
        if(!userEntity.getStatus().equals(UserStatus.ACTIVATED)) {
            throw new NotActivatedException(NOT_VERIFIED_RESPONSE);
        }

        return jwtHandler.generateUserAccessToken(userMapper.userEntityToUserShortDto(userEntity), userEntity.getMail());
    }
    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser() {
        return userService.get(holder.getUser().getMail());
    }

}
