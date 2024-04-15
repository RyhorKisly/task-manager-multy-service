package by.itacademy.taskservice.service.api;


import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;

import java.util.UUID;

public interface IAuditInteractService {
    void send(UserShortDTO userShortDTO, UUID projectUuid, String text, EssenceType essenceType);
    void send(UUID projectUuid, String text, EssenceType essenceType);


}
