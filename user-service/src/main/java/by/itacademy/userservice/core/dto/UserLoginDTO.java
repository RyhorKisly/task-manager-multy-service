package by.itacademy.userservice.core.dto;

import by.itacademy.sharedresource.core.validatiors.annotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO {
    @ValidEmail(message = "{incorrect.entered.email}")
    @NotBlank(message = "{user.mandatory.email}")
    private String mail;
    @NotBlank(message = "{user.mandatory.password}")
    @Size(min = 4, message = "{user.short.password}")
    @Size(max = 20, message = "{user.long.password}")
    private String password;
}
