package by.itacademy.taskservice.service.api;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserHolder {
    UserShortDTO getUser();
}
