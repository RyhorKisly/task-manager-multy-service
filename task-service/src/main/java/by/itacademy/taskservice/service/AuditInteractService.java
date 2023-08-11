package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;
import by.itacademy.taskservice.endpoints.utils.JwtTokenHandler;
import by.itacademy.taskservice.service.api.IAuditInteractService;
import by.itacademy.taskservice.service.feign.AuditServiceClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditInteractService
        implements IAuditInteractService
{
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
    public void send(UserShortDTO userShortDTO, UUID projectUuid, String text) {

        AuditCreateDTO auditCreateDTO = fillUserSendDTO(userShortDTO, projectUuid, text);
        String bearerToken = "Bearer " + jwtHandler.generateSystemAccessToken("System");
        auditServiceClient.send(bearerToken, auditCreateDTO);
    }

    private AuditCreateDTO fillUserSendDTO(UserShortDTO userShortDTO, UUID projectUuid, String text) {
        AuditCreateDTO auditCreateDTO = new AuditCreateDTO();

        //user, который произвёл операцию
        auditCreateDTO.setUserShortDTO(userShortDTO);

        //EssenceType для аудита
        auditCreateDTO.setType(EssenceType.PROJECT);
        auditCreateDTO.setText(text);
        //id кого создали
        auditCreateDTO.setId(projectUuid.toString());

        return auditCreateDTO;
    }
}
