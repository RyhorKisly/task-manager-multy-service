package by.itacademy.taskservice.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserRefEntity {
    @Id
    private UUID uuid;

    public UserRefEntity() {
    }

    public UserRefEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
