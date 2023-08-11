package by.itacademy.taskservice.service.api;


import by.itacademy.sharedresource.core.dto.UserShortDTO;

import java.util.UUID;

public interface IAuditInteractService {
    void send(UserShortDTO userShortDTO, UUID projectUuid, String text);

}
