package by.itacademy.taskservice.service.api;

import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ITaskService {
    TaskEntity create(@Valid TaskCreateDTO dto, String token);
}
