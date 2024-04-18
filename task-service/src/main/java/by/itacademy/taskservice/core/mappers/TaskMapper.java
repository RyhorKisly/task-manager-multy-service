package by.itacademy.taskservice.core.mappers;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.dto.ProjectRefDTO;
import by.itacademy.taskservice.core.dto.TaskDTO;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "dtCreate", expression = "java(formatData(taskEntity.getDtCreate()))")
    @Mapping(target = "dtUpdate", expression = "java(formatData(taskEntity.getDtUpdate()))")
    @Mapping(target = "project", expression = "java(createProjectRefDTO(taskEntity.getProject()))")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "implementer", expression = "java(createUserRefDTO(taskEntity.getImplementer()))")
    TaskDTO taskEntityToTaskDTO(TaskEntity taskEntity);

    default Long formatData(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    default ProjectRefDTO createProjectRefDTO(UUID uuid) {
        return ProjectRefDTO.builder().uuid(uuid).build();
    }

    default UserRefDTO createUserRefDTO(UUID uuid) {
        UserRefDTO userRefDTO = new UserRefDTO();
        userRefDTO.setUuid(uuid);
        return userRefDTO;
    }
}
