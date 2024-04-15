package by.itacademy.userservice.service.authentification;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.service.api.IUserHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder implements IUserHolder {
    @Override
    public UserDTO getUser(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
