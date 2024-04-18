package by.itacademy.taskservice.service.api;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
@Validated
public interface IProjectService {
    void create(@Valid ProjectCreateDTO dto);
    Page<ProjectDTO> get(PageRequest pageRequest, boolean archived);
    ProjectDTO get(UUID projectUuid);
    List<ProjectDTO> getByUser(UUID userUuid);
    List<ProjectDTO> get(List<UUID> projectUuids, UUID user);
    boolean ifExist(UUID project, UUID implementer);
    ProjectDTO update(@Valid ProjectCreateDTO dto, CoordinatesDTO coordinatesDTO);

}
