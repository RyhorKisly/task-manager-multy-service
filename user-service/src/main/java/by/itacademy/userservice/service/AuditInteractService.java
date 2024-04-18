package by.itacademy.userservice.service;

import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;
import by.itacademy.userservice.config.properites.JWTProperty;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.api.IAuditInteractService;
import by.itacademy.userservice.service.feign.AuditServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditInteractService implements IAuditInteractService {
    private final AuditServiceClient auditServiceClient;
    private final JwtTokenHandler jwtHandler;
    private final JWTProperty property;

    @Override
    public void send(UUID uuid, UserShortDTO userShortDTO, String text) {
        AuditCreateDTO auditCreateDTO = fillUserSendDTO(userShortDTO, uuid, text);
        String bearerToken = "Bearer " + jwtHandler.generateSystemAccessToken(property.getSystem());
        auditServiceClient.send(bearerToken, auditCreateDTO);
    }

    @Override
    public void send(UserShortDTO userShortDTO, String text) {
        AuditCreateDTO auditCreateDTO = fillUserSendDTO(userShortDTO, text);
        String bearerToken = "Bearer " + jwtHandler.generateSystemAccessToken(property.getSystem());
        auditServiceClient.send(bearerToken, auditCreateDTO);
    }

    private AuditCreateDTO fillUserSendDTO(UserShortDTO userShortDTO, String text) {
        AuditCreateDTO auditCreateDTO = new AuditCreateDTO();

        //user, который произвёл операцию
        auditCreateDTO.setUserShortDTO(userShortDTO);

        //EssenceType для аудита
        auditCreateDTO.setType(EssenceType.USER);
        auditCreateDTO.setText(text);
        //id кого создали
        auditCreateDTO.setId(userShortDTO.getUuid().toString());

        return auditCreateDTO;
    }

    private AuditCreateDTO fillUserSendDTO(UserShortDTO userShortDTO, UUID uuid, String text) {
        AuditCreateDTO auditCreateDTO = new AuditCreateDTO();

        //user, который произвёл операцию
        auditCreateDTO.setUserShortDTO(userShortDTO);

        //EssenceType для аудита
        auditCreateDTO.setType(EssenceType.USER);
        auditCreateDTO.setText(text);
        //id кого создали
        auditCreateDTO.setId(uuid.toString());

        return auditCreateDTO;
    }
}
