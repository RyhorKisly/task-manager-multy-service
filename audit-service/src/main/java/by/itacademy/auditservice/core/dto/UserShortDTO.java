package by.itacademy.auditservice.core.dto;


import by.itacademy.auditservice.core.enums.UserRole;
import by.itacademy.auditservice.core.validatiors.annotations.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserShortDTO {
    @NotNull(message = "Uuid is mandatory")
    private UUID uuid;
    @ValidEmail
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotBlank(message = "Fio is mandatory")
    private String fio;
    @NotNull(message = "Role is mandatory")
    private UserRole role;

    public UserShortDTO() {
    }

    public UserShortDTO(UUID uuid, String mail, String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
}
