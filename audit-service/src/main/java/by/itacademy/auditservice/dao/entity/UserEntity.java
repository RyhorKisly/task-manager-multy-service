package by.itacademy.auditservice.dao.entity;

import by.itacademy.sharedresource.core.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private UUID uuid;

    private String mail;

    private String fio;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
