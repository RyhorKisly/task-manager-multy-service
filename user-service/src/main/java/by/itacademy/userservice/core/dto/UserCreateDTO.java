package by.itacademy.userservice.core.dto;

import by.itacademy.userservice.core.enums.UserRole;
import by.itacademy.userservice.core.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {
    @Email(message = "Email entered incorrectly")
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotBlank(message = "Fio is mandatory")
    private String fio;
    @NotNull(message = "Role is mandatory")
    private UserRole role;
    @NotNull(message = "Status is mandatory")
    private UserStatus status;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, message = "Password too short")
    @Size(max = 20, message = "Password too long")
    private String password;

    public UserCreateDTO() {

    }

    public UserCreateDTO(
            String mail,
            String fio,
            UserRole role,
            UserStatus status,
            String password
    ) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
