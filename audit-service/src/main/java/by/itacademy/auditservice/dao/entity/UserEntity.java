package by.itacademy.auditservice.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user", schema = "app")
public class UserEntity {
    @Id
    private UUID uuid;
    private String mail;
    private String fio;
    private String role;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String mail, String fio, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
