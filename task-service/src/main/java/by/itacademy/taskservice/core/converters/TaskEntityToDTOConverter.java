package by.itacademy.taskservice.core.converters;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.dto.ProjectRefDTO;
import by.itacademy.taskservice.core.dto.TaskDTO;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class TaskEntityToDTOConverter implements Converter<TaskEntity, TaskDTO> {
    @Override
    public TaskDTO convert(TaskEntity entity) {
        TaskDTO dto = new TaskDTO();
        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setDtUpdate(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setProject(new ProjectRefDTO(entity.getProject()));
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setImplementer(new UserRefDTO(entity.getImplementer()));
        return dto;
    }
}
