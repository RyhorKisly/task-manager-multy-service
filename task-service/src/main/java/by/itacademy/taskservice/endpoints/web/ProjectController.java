package by.itacademy.taskservice.endpoints.web;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import by.itacademy.taskservice.endpoints.web.api.ProjectControllerApi;
import by.itacademy.taskservice.service.api.IProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController implements ProjectControllerApi {
    private final IProjectService projectService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<?> save(ProjectCreateDTO projectCreateDTO) {
        projectService.create(projectCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<ProjectDTO>> getPages(Integer page, Integer size, boolean archived) {
        Page<ProjectDTO> pageOfProjects =  projectService.get(PageRequest.of(page, size), archived);
        return new ResponseEntity<>(pageOfProjects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDTO> getCard(UUID uuid) {
        ProjectDTO projectDTO = conversionService.convert(projectService.get(uuid), ProjectDTO.class);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> update(UUID uuid, LocalDateTime dtUpdate, ProjectCreateDTO projectCreateDTO) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setUuid(uuid);
        coordinatesDTO.setDtUpdate(dtUpdate);
        projectService.update(projectCreateDTO, coordinatesDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
