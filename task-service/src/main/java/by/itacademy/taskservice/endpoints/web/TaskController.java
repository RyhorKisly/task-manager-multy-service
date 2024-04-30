package by.itacademy.taskservice.endpoints.web;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.taskservice.core.dto.FilterDTO;
import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.TaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.endpoints.web.api.TaskControllerApi;
import by.itacademy.taskservice.service.api.ITaskService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController implements TaskControllerApi {

    private final ITaskService taskService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<?> save(TaskCreateDTO taskCreateDTO) {
        taskService.create(taskCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<TaskDTO>> getPages(
            Integer page,
            Integer size,
            List<UUID> project,
            List<UUID> implementer,
            List<TaskStatus> status
    ) {
        FilterDTO filterDTO = new FilterDTO(project, implementer, status);
        Page<TaskDTO> pageOfTasks =  taskService.get(PageRequest.of(page, size), filterDTO);
        return new ResponseEntity<>(pageOfTasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDTO> getCard(UUID uuid) {
        TaskDTO taskDTO = conversionService.convert(taskService.get(uuid), TaskDTO.class);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDTO> update(UUID uuid, LocalDateTime dtUpdate, TaskCreateDTO taskCreateDTO) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setUuid(uuid);
        coordinatesDTO.setDtUpdate(dtUpdate);
        TaskDTO taskDTO = taskService.update(taskCreateDTO, coordinatesDTO);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDTO> statusPatch(UUID uuid, LocalDateTime dtUpdate, TaskStatus status){
        CoordinatesDTO coordinates = new CoordinatesDTO();
        coordinates.setUuid(uuid);
        coordinates.setDtUpdate(dtUpdate);
        TaskDTO taskDTO = taskService.updateStatus(status, coordinates);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
}
