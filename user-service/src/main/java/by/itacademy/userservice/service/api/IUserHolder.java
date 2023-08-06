package by.itacademy.userservice.service.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserHolder {
    UserDetails getUser();
}
