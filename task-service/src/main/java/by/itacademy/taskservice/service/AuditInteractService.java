package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.AuditCreateDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;
import by.itacademy.taskservice.endpoints.utils.JwtTokenHandler;
import by.itacademy.taskservice.service.api.IAuditInteractService;
import by.itacademy.taskservice.service.api.IUserHolder;
import by.itacademy.taskservice.service.feign.AuditServiceClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuditInteractService
        implements IAuditInteractService
{
    private final AuditServiceClient auditServiceClient;
    private final JwtTokenHandler jwtHandler;
    private final IUserHolder holder;
    public AuditInteractService(
            AuditServiceClient auditServiceClient,
            JwtTokenHandler jwtHandler,
            IUserHolder holder
    ) {
        this.auditServiceClient = auditServiceClient;
        this.jwtHandler = jwtHandler;
        this.holder = holder;
    }
//TODO Eсли Илья даст дабро брать инфу юзера из токена, то удалить этот метод и использовать тот который нижe
    @Override
    public void send(UserShortDTO userShortDTO, UUID projectUuid, String text, EssenceType essenceType) {

        AuditCreateDTO auditCreateDTO = fillUserSendDTO(userShortDTO, projectUuid, text, essenceType);
        String bearerToken = "Bearer " + jwtHandler.generateSystemAccessToken("System");
        auditServiceClient.send(bearerToken, auditCreateDTO);
    }

    @Override
    public void send(UUID projectUuid, String text, EssenceType essenceType) {
        UserShortDTO userShortDTO = holder.getUser();

        AuditCreateDTO auditCreateDTO = fillUserSendDTO(userShortDTO, projectUuid, text, essenceType);
        String bearerToken = "Bearer " + jwtHandler.generateSystemAccessToken("System");
        auditServiceClient.send(bearerToken, auditCreateDTO);
    }

    private AuditCreateDTO fillUserSendDTO(
            UserShortDTO userShortDTO,
            UUID projectUuid,
            String text,
            EssenceType essenceType
    ) {
        AuditCreateDTO auditCreateDTO = new AuditCreateDTO();

        //user, который произвёл операцию
        auditCreateDTO.setUserShortDTO(userShortDTO);

        //EssenceType для аудита
        auditCreateDTO.setType(essenceType);
        auditCreateDTO.setText(text);
        //id кого создали
        auditCreateDTO.setId(projectUuid.toString());

        return auditCreateDTO;
    }
}
