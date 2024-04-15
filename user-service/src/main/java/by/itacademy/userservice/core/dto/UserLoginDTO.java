package by.itacademy.userservice.core.dto;

import by.itacademy.sharedresource.core.validatiors.annotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @ValidEmail(message = "Email entered incorrectly")
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, message = "Password too short. Must be more 20 symbols")
    @Size(max = 20, message = "Password too long. Must be less 20 symbols")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(
            String mail,
            String password
    ) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
