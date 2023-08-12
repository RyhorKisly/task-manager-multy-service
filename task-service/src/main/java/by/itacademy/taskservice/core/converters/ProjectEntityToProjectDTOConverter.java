package by.itacademy.taskservice.core.converters;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import org.springframework.core.convert.converter.Converter;

import java.lang.annotation.Annotation;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectEntityToProjectDTOConverter implements Converter<ProjectEntity, ProjectDTO> {
    @Override
    public ProjectDTO convert(ProjectEntity entity) {
        List<UserRefDTO> userRefDTOS = new ArrayList<>();
        for (UUID staff : entity.getStaff()) {
            userRefDTOS.add(new UserRefDTO(staff));
        }

        ProjectDTO dto = new ProjectDTO();
        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setDtUpdate(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setManager(new UserRefDTO(entity.getUuid()));
        dto.setStaff(userRefDTOS);
        dto.setStatus(entity.getStatus());

        return dto;
    }

}
