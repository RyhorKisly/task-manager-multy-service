package by.itacademy.taskservice.service.api;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
@Validated
public interface IProjectService {
    ProjectEntity create(@Valid ProjectCreateDTO dto);
    Page<ProjectEntity> get(PageRequest pageRequest, boolean archived);
    ProjectEntity get(UUID projectUuid);
    List<ProjectEntity> getByUser(UUID userUuid);
    List<ProjectEntity> get(List<UUID> projectUuids, UUID user);
    boolean ifExist(UUID project, UUID implementer);
    ProjectEntity update(@Valid ProjectCreateDTO dto, CoordinatesDTO coordinatesDTO);

}
