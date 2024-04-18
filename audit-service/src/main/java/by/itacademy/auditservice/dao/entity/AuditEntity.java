package by.itacademy.auditservice.dao.entity;

import by.itacademy.sharedresource.core.enums.EssenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
