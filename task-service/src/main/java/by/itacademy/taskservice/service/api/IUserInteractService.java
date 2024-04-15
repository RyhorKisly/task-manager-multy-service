package by.itacademy.taskservice.service.api;

import by.itacademy.sharedresource.core.dto.UserShortDTO;

import java.util.List;
import java.util.UUID;

public interface IUserInteractService {
    UserShortDTO sendAndGet(String bearerToken);
    void check(List<UUID> uuids);
}
