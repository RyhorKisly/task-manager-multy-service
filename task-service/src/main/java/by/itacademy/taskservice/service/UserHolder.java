package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.taskservice.service.api.IUserHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserHolder implements IUserHolder {
    @Override
    public UserShortDTO getUser(){
        return (UserShortDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
