package by.itacademy.taskservice.dao.entity;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects", schema = "app")
public class ProjectEntity {
    @Id
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
    private String name;
    private String description;
    private UUID manager;
    @ElementCollection
    @CollectionTable(name = "projects_staff", joinColumns = @JoinColumn(name = "project_uuid"))
    @Column(name = "staff_uuid")
    private List<UUID> staff;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public ProjectEntity() {
    }

    public ProjectEntity(
            UUID uuid,
            LocalDateTime dtCreate,
            LocalDateTime dtUpdate,
            String name,
            String description,
            UUID manager,
            List<UUID> staff,
            ProjectStatus status
    ) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
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

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getManager() {
        return manager;
    }

    public void setManager(UUID manager) {
        this.manager = manager;
    }

    public List<UUID> getStaff() {
        return staff;
    }

    public void setStaff(List<UUID> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
