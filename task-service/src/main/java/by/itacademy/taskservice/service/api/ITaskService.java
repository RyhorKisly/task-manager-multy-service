package by.itacademy.taskservice.service.api;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.taskservice.core.dto.FilterDTO;
import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface ITaskService {
    TaskEntity create(@Valid TaskCreateDTO dto);
    Page<TaskEntity> get(PageRequest pageRequest, FilterDTO filterDTO);
    TaskEntity get(UUID taskUuid);
    TaskEntity update(@Valid TaskCreateDTO dto, CoordinatesDTO coordinates);
    TaskEntity updateStatus(TaskStatus status, CoordinatesDTO coordinates);
}
