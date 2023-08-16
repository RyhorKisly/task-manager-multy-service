package by.itacademy.taskservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskDTO {
    private UUID uuid;
    private Long dtCreate;
    private Long dtUpdate;
    private ProjectRefDTO project;
    private String title;
    private String description;
    private TaskStatus status;
    private UserRefDTO implementer;

    public TaskDTO() {
    }

    public TaskDTO(
            UUID uuid,
            Long dtCreate,
            Long dtUpdate,
            ProjectRefDTO project,
            String title,
            String description,
            TaskStatus status,
            UserRefDTO implementer
    ) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
    }

    public Long getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public ProjectRefDTO getProject() {
        return project;
    }

    public void setProject(ProjectRefDTO project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserRefDTO getImplementer() {
        return implementer;
    }

    public void setImplementer(UserRefDTO implementer) {
        this.implementer = implementer;
    }
}
