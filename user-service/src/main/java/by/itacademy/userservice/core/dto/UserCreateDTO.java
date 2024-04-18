package by.itacademy.userservice.core.dto;

import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.sharedresource.core.validatiors.annotations.ValidEmail;
import by.itacademy.userservice.core.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDTO {
    @ValidEmail
    @NotBlank(message = "{user.mandatory.email}")
    private String mail;
    @NotBlank(message = "{user.mandatory.fio}")
    private String fio;
    @NotNull(message = "{user.mandatory.role}")
    private UserRole role;
    @NotNull(message = "{user.mandatory.status}")
    private UserStatus status;
    @NotBlank(message = "{user.mandatory.password}")
    @Size(min = 4, message = "{user.short.password}")
    @Size(max = 20, message = "{user.long.password}")
    private String password;
}
