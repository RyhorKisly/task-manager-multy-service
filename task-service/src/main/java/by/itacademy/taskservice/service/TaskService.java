package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.enums.EssenceType;
import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.core.exceptions.FindEntityException;
import by.itacademy.taskservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import by.itacademy.taskservice.dao.repositories.ITaskDao;
import by.itacademy.taskservice.service.api.IAuditInteractService;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.api.ITaskService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TaskService implements ITaskService {
    private static final String TITLE_UNIQUE_CONSTRAINT = "tasks_title_unique";
    private static final String TASK_EXIST_RESPONSE = "Task with this title exists";
    private static final String TASK_CREATED = "Task: \"%s\" was created";
    private final IProjectService projectService;
    private final ITaskDao taskDao;
    private final IAuditInteractService auditInteractService;

    public TaskService(
            IProjectService projectService,
            ITaskDao taskDao,
            IAuditInteractService auditInteractService
    ) {
        this.projectService = projectService;
        this.taskDao = taskDao;
        this.auditInteractService = auditInteractService;
    }

    @Override
    @Transactional
    public TaskEntity create(TaskCreateDTO dto, String token) {

        if(projectService.ifExist(dto.getProject().getUuid(), dto.getImplementer().getUuid())) {
            throw new FindEntityException("метод работает");
        } else {
            throw new FindEntityException("метод не работает");
        }

//        TaskEntity entity = convertDTOToEntity(dto);
//        checkAndSaveEntity(entity);

//        String text =  String.format(TASK_CREATED, dto.getTitle());
//        auditInteractService.send(entity.getUuid(), text, token, EssenceType.TASK);
//        return entity;
    }

    private TaskEntity convertDTOToEntity(TaskCreateDTO dto) {
        TaskEntity entity = new TaskEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setProject(dto.getProject().getUuid());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setImplementer(dto.getImplementer().getUuid());
        return entity;
    }

    private TaskEntity checkAndSaveEntity(TaskEntity entity) {
        try {
            entity = taskDao.saveAndFlush(entity);
        } catch (DataAccessException ex) {
            if (ex.getMessage().contains(TITLE_UNIQUE_CONSTRAINT)) {
                throw new DataIntegrityViolationException(TASK_EXIST_RESPONSE, ex);
            } else {
                throw new UndefinedDBEntityException(ex.getMessage(), ex);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException (ex.getMessage(), ex);
        }
        return entity;
    }
}
