package by.itacademy.taskservice.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "user_ref_entity", schema="app")
public class UserRefEntity {
    @Id
    @GeneratedValue
    private Long id;

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
