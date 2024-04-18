package by.itacademy.taskservice.dao.entity;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects",
        uniqueConstraints = @UniqueConstraint(name = "projects_name_unique", columnNames = {"name"}))
public class ProjectEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @NotNull(message = "{project.null.name}")
    @Column(name = "name")
    private String name;

    @NotNull(message = "{project.null.description}")
    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "manager", foreignKey = @ForeignKey(name = "project_users_foreign_key"))
    private UserRefEntity manager;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name="projects_staff",
            joinColumns= @JoinColumn(name="project_uuid"),
            foreignKey = @ForeignKey(name = "projects_staff_project_uuid_fkey"),
            inverseJoinColumns= @JoinColumn(name="staff_uuid"),
            inverseForeignKey = @ForeignKey(name = "users_staff_uuid_fkey")
    )
    private List<UserRefEntity> staff;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
}
