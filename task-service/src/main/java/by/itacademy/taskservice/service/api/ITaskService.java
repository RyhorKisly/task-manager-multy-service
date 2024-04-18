package by.itacademy.taskservice.service.api;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.taskservice.core.dto.FilterDTO;
import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.TaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface ITaskService {
    TaskDTO create(@Valid TaskCreateDTO dto);
    Page<TaskDTO> get(PageRequest pageRequest, FilterDTO filterDTO);
    TaskDTO get(UUID taskUuid);
    TaskDTO update(@Valid TaskCreateDTO dto, CoordinatesDTO coordinates);
    TaskDTO updateStatus(TaskStatus status, CoordinatesDTO coordinates);
}
