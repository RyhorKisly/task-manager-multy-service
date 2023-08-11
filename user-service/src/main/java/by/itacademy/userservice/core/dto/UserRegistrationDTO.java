package by.itacademy.userservice.core.dto;

import by.itacademy.sharedresource.core.validatiors.annotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @ValidEmail
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotBlank(message = "Fio is mandatory")
    private String fio;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, message = "Password too short. Must be more 20 symbols")
    @Size(max = 20, message = "Password too long. Must be less 20 symbols")
    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(
            String mail,
            String fio,
            String password
    ) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
