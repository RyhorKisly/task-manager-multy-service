package by.itacademy.taskservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private UUID uuid;
    private Long dtCreate;
    private Long dtUpdate;
    private String name;
    private String description;
    private UserRefDTO manager;
    private List<UserRefDTO> staff;
    private ProjectStatus status;
}
