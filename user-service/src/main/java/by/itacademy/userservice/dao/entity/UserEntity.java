package by.itacademy.userservice.dao.entity;

import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.userservice.core.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @UpdateTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @NotNull(message = "{user.null.mail}")
    @Column(name = "mail")
    private String mail;

    @NotNull(message = "{user.null.fio}")
    @Column(name = "fio")
    private String fio;

    @NotNull(message = "{user.null.role}")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull(message = "{user.null.status}")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotNull(message = "{user.null.password}")
    @Column(name = "password")
    private String password;
}