package by.itacademy.userservice.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "verification")
public class VerificationEntity {

    @Id
    private UUID uuid;
    private String mail;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    public VerificationEntity() {
    }

    public VerificationEntity(UUID uuid, String mail, LocalDateTime dtCreate) {
        this.uuid = uuid;
        this.mail = mail;
        this.dtCreate = dtCreate;
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

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }
}
