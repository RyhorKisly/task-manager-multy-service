package by.itacademy.auditservice.dao.entity;

import by.itacademy.sharedresource.core.enums.EssenceType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit")
public class AuditEntity {
    @Id
    private UUID uuid;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private UserEntity user;
    private String text;
    @Enumerated(EnumType.STRING)
    private EssenceType type;
    private String id;

    public AuditEntity() {
    }

    public AuditEntity(
            UUID uuid,
            LocalDateTime dtCreate,
            UserEntity user,
            String text,
            EssenceType type,
            String id
    ) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
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

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EssenceType getType() {
        return type;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
