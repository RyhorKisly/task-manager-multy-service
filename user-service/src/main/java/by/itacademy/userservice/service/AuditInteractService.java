package by.itacademy.userservice.service;

import by.itacademy.userservice.core.dto.AuditSendDTO;
import by.itacademy.userservice.core.dto.UserShortDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.IAuditInteractService;
import by.itacademy.userservice.service.feign.AuditServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditInteractService implements IAuditInteractService {
    private final AuditServiceClient auditServiceClient;
    private final JwtTokenHandler jwtHandler;
    public AuditInteractService(
            AuditServiceClient auditServiceClient,
            JwtTokenHandler jwtHandler
    ) {
        this.auditServiceClient = auditServiceClient;
        this.jwtHandler = jwtHandler;
    }

    @Override
    @Transactional(readOnly = true)
    public void send(UserEntity newEntity, UserShortDTO userShortDTO, String text) {
        AuditSendDTO auditSendDTO = fillUserSendDTO(userShortDTO, newEntity, text);
        String bearerToken = "Bearer " + jwtHandler.generateAccessToken("System");
        auditServiceClient.send(bearerToken, auditSendDTO);
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
}
