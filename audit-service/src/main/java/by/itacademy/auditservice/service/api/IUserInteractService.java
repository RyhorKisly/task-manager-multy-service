package by.itacademy.auditservice.service.api;

import by.itacademy.auditservice.core.dto.UserShortDTO;

public interface IUserInteractService {
    UserShortDTO sendAndGet(String bearerToken);
}
