package by.itacademy.taskservice.core.dto;

import by.itacademy.taskservice.core.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    private List<UUID> projects;
    private List<UUID> implementers;
    private List<TaskStatus> statuses;
}
