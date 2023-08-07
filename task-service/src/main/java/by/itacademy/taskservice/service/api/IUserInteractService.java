package by.itacademy.taskservice.service.api;

import by.itacademy.taskservice.core.dto.UserShortDTO;

public interface IUserInteractService {
    UserShortDTO sendAndGet(String bearerToken);
}
