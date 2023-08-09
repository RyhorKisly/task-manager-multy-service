package by.itacademy.taskservice.core.dto;

import by.itacademy.taskservice.core.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserShortDTO {
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotNull(message = "Role is mandatory")
    private UserRole role;

    public UserShortDTO() {
    }

    public UserShortDTO(String mail, UserRole role) {
        this.mail = mail;
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
