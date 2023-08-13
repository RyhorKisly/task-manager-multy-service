package by.itacademy.taskservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskCreateDTO {
    @NotNull
    private ProjectRefDTO project;
    @NotBlank
    private String title;
    private String description;
    private TaskStatus status;
    private UserRefDTO implementer;

    public TaskCreateDTO() {
    }

    public TaskCreateDTO(
            @NotNull ProjectRefDTO project,
            String title,
            String description,
            TaskStatus status,
            UserRefDTO implementer
    ) {
        this.project = project;
        this.title = title;
        this.description = description;
        this.status = status;
        this.implementer = implementer;
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
