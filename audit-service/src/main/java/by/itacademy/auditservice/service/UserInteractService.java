package by.itacademy.auditservice.service;

import by.itacademy.auditservice.service.api.IUserInteractService;
import by.itacademy.auditservice.service.feign.UserServiceClient;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.springframework.stereotype.Service;

@Service
public class UserInteractService implements IUserInteractService {
    private final UserServiceClient userServiceClient;

    public UserInteractService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserShortDTO sendAndGet(String bearerToken) {
        return userServiceClient.send("Bearer " + bearerToken).getBody();
    }
}
