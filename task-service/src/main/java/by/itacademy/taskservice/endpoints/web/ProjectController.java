package by.itacademy.taskservice.endpoints.web;

import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.service.api.IProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(
            IProjectService projectService
    ) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody @Valid ProjectCreateDTO projectCreateDTO
            ) {
        projectService.create(projectCreateDTO, bearerToken);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
