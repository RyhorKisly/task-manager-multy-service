package by.itacademy.taskservice.core.mappers;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import by.itacademy.taskservice.dao.entity.UserRefEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    UserRefEntity userRefDTOToUserRefEntity(UserRefDTO userRefDTO);
    UserRefDTO userRefEntityToUserRefDTO(UserRefEntity userRefEntity);
    List<UserRefEntity> userRefDTOsToUserFerEntities(List<UserRefDTO> userRefDTO);
    List<UserRefDTO> userRefEntitiesToUserRefDTOs(List<UserRefEntity> userRefEntities);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "dtCreate", ignore = true)
    @Mapping(target = "dtUpdate", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "staff", source = "staff")
    @Mapping(target = "status", source = "status")
    ProjectEntity projectCreateDTOToProjectEntity(ProjectCreateDTO projectCreateDTO);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "dtCreate", expression = "java(formatData(projectEntity.getDtCreate()))")
    @Mapping(target = "dtUpdate", expression = "java(formatData(projectEntity.getDtUpdate()))")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "staff", source = "staff")
    @Mapping(target = "status", source = "status")
    ProjectDTO projectEntityToProjectDTO(ProjectEntity projectEntity);

    List<ProjectDTO> projectEntitiesToProjectDTOs(List<ProjectEntity> projectEntities);

    default Long formatData(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
