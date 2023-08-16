package by.itacademy.taskservice.endpoints.web;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.sharedresource.core.dto.PageDTO;
import by.itacademy.taskservice.core.dto.FilterDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.TaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import by.itacademy.taskservice.service.api.ITaskService;
import by.itacademy.taskservice.service.utils.PageConverter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService taskService;
    private final ConversionService conversionService;
    private final PageConverter pageConverter;

    public TaskController(
            ITaskService taskService,
            ConversionService conversionService,
            PageConverter pageConverter
    ) {
        this.taskService = taskService;
        this.conversionService = conversionService;
        this.pageConverter = pageConverter;
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid TaskCreateDTO taskCreateDTO
            ) {
        taskService.create(taskCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageDTO<TaskDTO>> getPages(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "20") @PositiveOrZero Integer size,
            @RequestParam(required = false) List<UUID> project,
            @RequestParam(required = false) List<UUID> implementer,
            @RequestParam(required = false) List<TaskStatus> status
    ) {
        FilterDTO filterDTO = new FilterDTO(project, implementer, status);
        Page<TaskEntity> pageOfTasks =  taskService.get(PageRequest.of(page, size), filterDTO);
        return new ResponseEntity<>(
                pageConverter.convertToPageDTO(pageOfTasks, TaskDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskDTO> getCard(
            @PathVariable UUID uuid
    ) {
        TaskDTO taskDTO = conversionService.convert(taskService.get(uuid), TaskDTO.class);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime dtUpdate,
            @RequestBody @Valid TaskCreateDTO taskCreateDTO
    ) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setUuid(uuid);
        coordinatesDTO.setDtUpdate(dtUpdate);
        taskService.update(taskCreateDTO, coordinatesDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{uuid}/dt_update/{dt_update}/status/{status}")
    public ResponseEntity<TaskDTO> statusPatch(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime dtUpdate,
            @PathVariable TaskStatus status){
        CoordinatesDTO coordinates = new CoordinatesDTO();
        coordinates.setUuid(uuid);
        coordinates.setDtUpdate(dtUpdate);
        taskService.updateStatus(status, coordinates);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
