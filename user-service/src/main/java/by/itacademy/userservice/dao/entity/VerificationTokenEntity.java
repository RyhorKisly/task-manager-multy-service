package by.itacademy.userservice.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "verification", schema = "app")
public class VerificationTokenEntity {

    @Id
    private UUID uuid;
    private UUID token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_uuid")
    private UserEntity user;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    public VerificationTokenEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public VerificationTokenEntity(UUID uuid, UUID token, UserEntity user, LocalDateTime dtCreate) {
        this.uuid = uuid;
        this.token = token;
        this.user = user;
        this.dtCreate = dtCreate;
    }
}
