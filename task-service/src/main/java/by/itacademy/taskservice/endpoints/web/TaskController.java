package by.itacademy.taskservice.endpoints.web;

import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.service.api.ITaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(
            ITaskService taskService
    ) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken,
            @RequestBody @Valid TaskCreateDTO taskCreateDTO
            ) {
        taskService.create(
                taskCreateDTO,
                bearerToken.split(" ")[1].trim()
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
