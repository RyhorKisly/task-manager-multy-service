package by.itacademy.taskservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProjectCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private UserRefDTO manager;
    @NotNull
    private List<UserRefDTO> staff;
    @NotNull
    private ProjectStatus status;
}
