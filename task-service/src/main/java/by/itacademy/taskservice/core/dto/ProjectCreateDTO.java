package by.itacademy.taskservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProjectCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private UserRefDTO manager;
    private List<UserRefDTO> staff;
    @NotNull
    private ProjectStatus status;

    public ProjectCreateDTO() {
    }

    public ProjectCreateDTO(
            String name,
            String description,
            UserRefDTO manager,
            List<UserRefDTO> staff,
            ProjectStatus status
    ) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
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

    public UserRefDTO getManager() {
        return manager;
    }

    public void setManager(UserRefDTO manager) {
        this.manager = manager;
    }

    public List<UserRefDTO> getStaff() {
        return staff;
    }

    public void setStaff(List<UserRefDTO> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
