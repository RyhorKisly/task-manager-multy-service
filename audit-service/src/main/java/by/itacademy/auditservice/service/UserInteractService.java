package by.itacademy.auditservice.service;

import by.itacademy.auditservice.service.api.IUserInteractService;
import by.itacademy.auditservice.service.feign.UserServiceClient;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInteractService implements IUserInteractService {
    private final UserServiceClient userServiceClient;

    @Override
    public UserShortDTO sendAndGet(String bearerToken) {
        return userServiceClient.send("Bearer " + bearerToken).getBody();
    }
}
