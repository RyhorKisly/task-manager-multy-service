package by.itacademy.taskservice.service.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserHolder {
    UserDetails getUser();
}
