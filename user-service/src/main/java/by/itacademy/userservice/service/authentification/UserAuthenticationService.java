package by.itacademy.userservice.service.authentification;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.exceptions.NotActivatedException;
import by.itacademy.sharedresource.core.exceptions.VerificationException;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.core.enums.UserStatus;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationEntity;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Validated
public class UserAuthenticationService implements IUserAuthenticationService {
    private static final String ERROR_VERIFY_RESPONSE = "Failed to verify user. Contact support!";
    private static final String WRONG_PASSWORD_RESPONSE = "Wrong password!";
    private static final String NOT_VERIFIED_RESPONSE = "The user is not activated. " +
            "To activate, follow the link sent to the email specified during registration. " +
            "If you didn't receive a link, please contact your administrator.";
    private static final String USER_REGISTERED = "User: %s was registered";
    private static final String USER_ACTIVATED = "User: %s was activated";
    private final IUserService userService;
    private final IVerificationService verificationService;
    private final IEmailService emailService;
    private final PasswordEncoder encoder;
    private final JwtTokenHandler jwtHandler;
    private final UserHolder holder;
    private final IAuditInteractService auditInteractService;

    public UserAuthenticationService(
            IUserService userService,
            IVerificationService verificationService,
            IEmailService emailService,
            PasswordEncoder encoder,
            JwtTokenHandler jwtHandler,
            UserHolder holder,
            IAuditInteractService auditInteractService
    ) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.encoder = encoder;
        this.jwtHandler = jwtHandler;
        this.holder = holder;
        this.auditInteractService = auditInteractService;
    }

    @Override
    @Transactional
    public void register(UserRegistrationDTO item) {
        UserEntity userEntity = userService.createWithRegistration(item);

        VerificationEntity verificationEntity = verificationService.save(userEntity);

        emailService.sendEmail(userEntity, verificationEntity);

        UserShortDTO userShortDTO = fillUserShortDTO(userEntity);
        String text = String.format(USER_REGISTERED, userEntity.getMail());
        auditInteractService.send(userEntity, userShortDTO, text);
    }

    @Override
    @Transactional
    public void verify(String code, String mail) {
        UserEntity userEntity;
        VerificationEntity verificationEntity;
        try {
            userEntity = userService.get(mail);
            verificationEntity = verificationService.get(mail);
        } catch (RuntimeException ex){
            throw new VerificationException(ERROR_VERIFY_RESPONSE, ex);
        }

        if(!verificationEntity.getUuid().equals(UUID.fromString(code))
                && !userEntity.getStatus().equals(UserStatus.WAITING_ACTIVATION)) {
            throw new VerificationException(ERROR_VERIFY_RESPONSE);
        }

        userService.activate(userEntity);

        verificationService.delete(verificationEntity.getUuid());
        verificationService.deleteByDtCreateLessThan(LocalDateTime.now().minusDays(7));

        UserShortDTO userShortDTO = fillUserShortDTO(userEntity);
        String text = String.format(USER_ACTIVATED, userEntity.getMail());
        auditInteractService.send(userEntity, userShortDTO, text);
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

        return jwtHandler.generateUserAccessToken(fillUserShortDTO(userEntity), userEntity.getMail());
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUser() {
        return userService.get(holder.getUser().getUsername());
    }

    @Override
    @Transactional(readOnly = true)
    public UserShortDTO getGeneralUser() {
        UserEntity userEntity = userService.get(holder.getUser().getUsername());

        return new UserShortDTO(
                userEntity.getUuid(),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRole()
        );
    }

    private UserShortDTO fillUserShortDTO(UserEntity entityEntity) {
        UserShortDTO userShortDTO = new UserShortDTO();
        userShortDTO.setUuid(entityEntity.getUuid());
        userShortDTO.setMail(entityEntity.getMail());
        userShortDTO.setFio(entityEntity.getFio());
        userShortDTO.setRole(entityEntity.getRole());
        return userShortDTO;
    }

}
