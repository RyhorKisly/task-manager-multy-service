package by.itacademy.taskservice.endpoints.web;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.sharedresource.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.core.converters.PageConverter;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final IProjectService projectService;
    private final ConversionService conversionService;
    private final PageConverter pageConverter;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid ProjectCreateDTO projectCreateDTO
            ) {
        projectService.create(projectCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageDTO<ProjectDTO>> getPages(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "20") @PositiveOrZero Integer size,
            @RequestParam(required = false, defaultValue = "false") boolean archived
    ) {
        Page<ProjectEntity> pageOfProjects =  projectService.get(PageRequest.of(page, size), archived);
        return new ResponseEntity<>(
                pageConverter.convertToPageDTO(pageOfProjects, ProjectDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProjectDTO> getCard(
            @PathVariable UUID uuid
    ) {
        ProjectDTO projectDTO = conversionService.convert(projectService.get(uuid), ProjectDTO.class);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime dtUpdate,
            @RequestBody @Valid ProjectCreateDTO projectCreateDTO
    ) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setUuid(uuid);
        coordinatesDTO.setDtUpdate(dtUpdate);
        projectService.update(projectCreateDTO, coordinatesDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
