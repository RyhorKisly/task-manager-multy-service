package by.itacademy.taskservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private UUID uuid;
    private Long dtCreate;
    private Long dtUpdate;
    private ProjectRefDTO project;
    private String title;
    private String description;
    private TaskStatus status;
    private UserRefDTO implementer;
}
