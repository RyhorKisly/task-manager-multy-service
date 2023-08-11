package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.taskservice.config.properites.JWTProperty;
import by.itacademy.taskservice.endpoints.utils.JwtTokenHandler;
import by.itacademy.taskservice.service.api.IUserInteractService;
import by.itacademy.taskservice.service.feign.UserServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserInteractService implements IUserInteractService {
    private final JwtTokenHandler jwtHandler;
    private final UserServiceClient userServiceClient;
    private final JWTProperty property;

    public UserInteractService(
            JwtTokenHandler jwtHandler,
            UserServiceClient userServiceClient,
            JWTProperty property
    ) {
        this.jwtHandler = jwtHandler;
        this.userServiceClient = userServiceClient;
        this.property = property;
    }

    @Override
    public UserShortDTO sendAndGet(String bearerToken) {
        return userServiceClient.send("Bearer " + bearerToken).getBody();
    }

    @Override
    public void check(List<UUID> uuids) {
        String bearerToken = "Bearer " + jwtHandler.generateSystemAccessToken(property.getSystem());
        userServiceClient.check(bearerToken, uuids);
    }
}
