package by.itacademy.userservice.core.dto;

import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.userservice.core.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserDTO {
    private UUID uuid;
    private Long dtCreate;
    private Long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
}
