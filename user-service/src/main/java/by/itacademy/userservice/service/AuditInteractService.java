package by.itacademy.userservice.service;

import by.itacademy.userservice.core.dto.AuditSendDTO;
import by.itacademy.userservice.core.dto.UserShortDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.IAuditInteractService;
import by.itacademy.userservice.service.api.IUserService;
import by.itacademy.userservice.service.authentification.UserHolder;
import by.itacademy.userservice.service.feign.AuditServiceClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditInteractService implements IAuditInteractService {
    private final AuditServiceClient auditServiceClient;
    private final JwtTokenHandler jwtHandler;
    private IUserService userService;
    private final UserHolder holder;
    public AuditInteractService(
            AuditServiceClient auditServiceClient,
            JwtTokenHandler jwtHandler,
            UserHolder holder
    ) {
        this.auditServiceClient = auditServiceClient;
        this.jwtHandler = jwtHandler;
        this.holder = holder;
    }

    @Override
    @Transactional(readOnly = true)
    public void send(UserEntity newEntity, String text) {
        UserDetails userDetails = holder.getUser();
        UserShortDTO userShortDTO = fillUserShortDTO(userService.get(userDetails.getUsername()));

        AuditSendDTO auditSendDTO = fillUserSendDTO(userShortDTO, newEntity, text);
        String bearerToken = "Bearer " + jwtHandler.generateAccessToken("System");
        auditServiceClient.send(bearerToken, auditSendDTO);
    }

    @Override
    public void send(UserEntity newEntity, UserEntity userEntity, String text) {
        UserShortDTO userShortDTO = fillUserShortDTO(userEntity);

        AuditSendDTO auditSendDTO = fillUserSendDTO(userShortDTO, newEntity, text);
        String bearerToken = "Bearer " + jwtHandler.generateAccessToken("System");
        auditServiceClient.send(bearerToken, auditSendDTO);
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    private AuditSendDTO fillUserSendDTO(UserShortDTO userShortDTO, UserEntity newEntity, String text) {
        AuditSendDTO auditSendDTO = new AuditSendDTO();

        //user, который произвёл операцию
        auditSendDTO.setUserGeneralDTO(userShortDTO);

        //EssenceType для аудита
        auditSendDTO.setType("USER");
        auditSendDTO.setText(text);
        //id кого создали
        auditSendDTO.setId(newEntity.getUuid().toString());

        return auditSendDTO;
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
