package by.itacademy.auditservice.dao.entity;

import by.itacademy.sharedresource.core.enums.EssenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "audit")
public class AuditEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private UserEntity user;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EssenceType type;

    @Column(name = "id")
    private String id;
}
