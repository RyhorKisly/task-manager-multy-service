package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;
import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.sharedresource.core.exceptions.NotVerifiedCoordinatesException;
import by.itacademy.taskservice.core.dto.FilterDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import by.itacademy.taskservice.core.dto.TaskCreateDTO;
import by.itacademy.taskservice.core.dto.TaskDTO;
import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.core.exceptions.FindEntityException;
import by.itacademy.taskservice.core.exceptions.ForbiddenEntityException;
import by.itacademy.taskservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.taskservice.core.mappers.TaskMapper;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import by.itacademy.taskservice.dao.repositories.ITaskDao;
import by.itacademy.taskservice.service.api.IAuditInteractService;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.api.ITaskService;
import by.itacademy.taskservice.service.api.IUserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static by.itacademy.taskservice.core.util.Messages.ERROR_UPDATE_RESPONSE_TASK;
import static by.itacademy.taskservice.core.util.Messages.REQUEST_ERROR;
import static by.itacademy.taskservice.core.util.Messages.TASK_CREATED;
import static by.itacademy.taskservice.core.util.Messages.TASK_EXIST_RESPONSE;
import static by.itacademy.taskservice.core.util.Messages.TASK_FORBIDDEN_RESPONSE;
import static by.itacademy.taskservice.core.util.Messages.TASK_NOT_EXIST_RESPONSE;
import static by.itacademy.taskservice.core.util.Messages.TASK_UPDATED;
import static by.itacademy.taskservice.core.util.Messages.TITLE_UNIQUE_CONSTRAINT;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final IProjectService projectService;
    private final ITaskDao taskDao;
    private final IAuditInteractService auditInteractService;
    private final IUserHolder holder;
    private final TaskMapper taskMapper;

    @Transactional
    @Override
    public TaskDTO create(TaskCreateDTO dto) {

        if(!projectService.ifExist(dto.getProject().getUuid(), dto.getImplementer().getUuid())) {
            throw new FindEntityException(REQUEST_ERROR);
        }

        TaskEntity entity = convertDTOToEntity(dto);
        entity = saveOrThrow(entity);

        String text =  String.format(TASK_CREATED, dto.getTitle());
        auditInteractService.send(entity.getUuid(), text, EssenceType.TASK);
        return taskMapper.taskEntityToTaskDTO(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TaskDTO> get(PageRequest pageRequest, FilterDTO filterDTO) {
        UserShortDTO userDTO = holder.getUser();

        Page<TaskEntity> taskEntities = null;
        if(userDTO.getRole().equals(UserRole.ADMIN)) {
            taskEntities = findPageForAdmin(filterDTO, pageRequest);
        }
        if(userDTO.getRole().equals(UserRole.USER)) {
            taskEntities = findPageForUser(userDTO, filterDTO, pageRequest);
        }
        return Objects.requireNonNull(taskEntities).map(taskMapper::taskEntityToTaskDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public TaskDTO get(UUID taskUuid) {
        UserShortDTO userDTO = holder.getUser();
        TaskEntity taskEntity;
        if(userDTO.getRole().equals(UserRole.ADMIN)) {
            taskEntity = taskDao.findById(taskUuid)
                    .orElseThrow(() -> new FindEntityException(TASK_NOT_EXIST_RESPONSE));
        } else {
            List<ProjectDTO> projectDTOs = projectService.getByUser(userDTO.getUuid());
            List<UUID> projectsUuid = (projectDTOs.stream().map(ProjectDTO::getUuid).toList());
            taskEntity = taskDao.findByUuidAndProjectIn(taskUuid, projectsUuid)
                    .orElseThrow(() -> new ForbiddenEntityException(TASK_FORBIDDEN_RESPONSE));
        }
        return taskMapper.taskEntityToTaskDTO(taskEntity);
    }

    @Transactional
    @Override
    public TaskDTO update(TaskCreateDTO createDTO, CoordinatesDTO coordinates) {
        TaskEntity taskEntity = taskDao.findById(coordinates.getUuid())
                .orElseThrow(() -> new FindEntityException(TASK_NOT_EXIST_RESPONSE));

        checkDtUpdate(taskEntity, coordinates);
        taskEntity = setFieldsToUpdate(taskEntity, createDTO);
        taskEntity = saveOrThrow(taskEntity);

        UserShortDTO userShortDTO = holder.getUser();
        String text =  String.format(TASK_UPDATED, taskEntity.getTitle());
        auditInteractService.send(userShortDTO, taskEntity.getUuid(),text, EssenceType.PROJECT);

        return taskMapper.taskEntityToTaskDTO(taskEntity);
    }
    @Transactional
    @Override
    public TaskDTO updateStatus(TaskStatus status, CoordinatesDTO coordinates) {
        TaskEntity taskEntity = taskDao.findById(coordinates.getUuid())
                .orElseThrow(() -> new FindEntityException(TASK_NOT_EXIST_RESPONSE));

        checkDtUpdate(taskEntity, coordinates);
        taskEntity.setStatus(status);
        taskEntity = saveOrThrow(taskEntity);

        UserShortDTO userShortDTO = holder.getUser();
        String text =  String.format(TASK_UPDATED, taskEntity.getTitle());
        auditInteractService.send(userShortDTO, taskEntity.getUuid(),text, EssenceType.PROJECT);

        return taskMapper.taskEntityToTaskDTO(taskEntity);
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

    private TaskEntity saveOrThrow(TaskEntity entity) {
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


    private Page<TaskEntity> findPageForAdmin(FilterDTO filterDTO, PageRequest pageRequest) {
        if (filterDTO.getStatuses() == null) {
            filterDTO.setStatuses(List.of(TaskStatus.values()));
        }
        if(filterDTO.getProjects() == null && filterDTO.getImplementers() == null) {
            return taskDao.findAll(pageRequest);
        } else if(filterDTO.getProjects() == null) {
            return taskDao.findByImplementerIn(filterDTO.getImplementers(), pageRequest);
        } else if(filterDTO.getImplementers() == null) {
            return taskDao.findByProjectIn(filterDTO.getProjects(), pageRequest);
        } else {
            return taskDao.findByProjectInAndStatusInAndImplementerIn(
                    filterDTO.getProjects(),
                    filterDTO.getStatuses(),
                    filterDTO.getImplementers(),
                    pageRequest
            );
        }
    }

    private Page<TaskEntity> findPageForUser(UserShortDTO userDto, FilterDTO filterDTO, PageRequest pageRequest) {
        List<ProjectDTO> projectDTOs = getFilteredProjects(userDto, filterDTO);

        if((filterDTO.getImplementers() == null)) {
            filterDTO.setImplementers(getImplementersFromAcceptedProjects(projectDTOs, filterDTO));
        }

        if (filterDTO.getStatuses() == null) {
            filterDTO.setStatuses(List.of(TaskStatus.values()));
        }
        return taskDao.findByProjectInAndStatusInAndImplementerIn(
                filterDTO.getProjects(),
                filterDTO.getStatuses(),
                filterDTO.getImplementers(),
                pageRequest
        );
    }
    private List<ProjectDTO> getFilteredProjects(UserShortDTO userDto, FilterDTO filterDTO) {
        List<ProjectDTO> projectDTOs;
        if(filterDTO.getProjects() == null) {
            projectDTOs = projectService.getByUser(userDto.getUuid());
            filterDTO.setProjects(projectDTOs.stream().map(ProjectDTO::getUuid).toList());
        } else {
            projectDTOs = projectService.get(filterDTO.getProjects(), userDto.getUuid());
            List<UUID> projects = new ArrayList<>();
            for (ProjectDTO dto : projectDTOs) {
                projects.add(dto.getUuid());
            }
            filterDTO.setProjects(projects);
        }
        return projectDTOs;
    }
    private List<UUID> getImplementersFromAcceptedProjects(List<ProjectDTO> projectDTOs, FilterDTO filterDTO) {
        Set<UUID> implementers = new HashSet<>();
        for (ProjectDTO dto : projectDTOs) {
            for (UserRefDTO staff : dto.getStaff()) {
                implementers.add(staff.getUuid());
            }
            implementers.add(dto.getManager().getUuid());
        }
        return new ArrayList<>(implementers);
    }

    private TaskEntity setFieldsToUpdate(TaskEntity entity, TaskCreateDTO dto) {
        entity.setProject(dto.getProject().getUuid());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setImplementer(dto.getImplementer().getUuid());
        return entity;
    }

    private void checkDtUpdate(TaskEntity entity, CoordinatesDTO coordinates) {
        if (!entity.getDtUpdate().withNano(0)
                .isEqual(coordinates.getDtUpdate().withNano(0))
        ) {
            throw new NotVerifiedCoordinatesException(ERROR_UPDATE_RESPONSE_TASK);
        }
    }
}
