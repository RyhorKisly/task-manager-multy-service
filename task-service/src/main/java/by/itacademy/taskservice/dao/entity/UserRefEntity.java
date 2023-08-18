package by.itacademy.taskservice.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_ref", schema = "task")
public class UserRefEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;

    public UserRefEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserRefEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public UserRefEntity(Long id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }
}
