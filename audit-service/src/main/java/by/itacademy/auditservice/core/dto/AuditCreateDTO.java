package by.itacademy.auditservice.core.dto;

import by.itacademy.auditservice.core.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AuditCreateDTO {
    @NotNull(message = "Uuid is mandatory")
    private UUID uuid;
    @Email(message = "Email entered incorrectly")
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotBlank(message = "Fio is mandatory")
    private String fio;
    @NotNull(message = "Role is mandatory")
    private UserRole role;
    @NotBlank(message = "Text is mandatory")
    private String text;
    @NotBlank(message = "Type is mandatory with UpperCase")
    private String type;
    @NotBlank(message = "Id is mandatory")
    private String id;

    public AuditCreateDTO() {
    }

    public AuditCreateDTO(UUID uuid, String mail, String fio, UserRole role, String text, String type, String id) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
