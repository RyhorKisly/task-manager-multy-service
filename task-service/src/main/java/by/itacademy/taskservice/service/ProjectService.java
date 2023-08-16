package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;
import by.itacademy.sharedresource.core.enums.UserRole;
import by.itacademy.sharedresource.core.exceptions.NotVerifiedCoordinatesException;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.core.exceptions.FindEntityException;
import by.itacademy.taskservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import by.itacademy.taskservice.dao.entity.UserRefEntity;
import by.itacademy.taskservice.dao.repositories.IProjectsDao;
import by.itacademy.taskservice.service.api.IAuditInteractService;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.api.IUserHolder;
import by.itacademy.taskservice.service.api.IUserInteractService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private static final String PROJECT_EXIST_RESPONSE = "Project with this name exists";
    private static final String NAME_UNIQUE_CONSTRAINT = "projects_name_unique";
    private static final String PROJECT_CREATED = "Project: \"%s\" was created";
    private static final String PROJECT_UPDATED = "Project: \"%s\" was updated";
    private static final String ERROR_GET_RESPONSE = "Failed to get project(s). Try again or contact support!";
    private static final String ERROR_GET_PAGE_BY_USER = "This user does not participate any project!";
    private static final String PROJECT_NOT_EXIST_RESPONSE = "Project with this id does not exist!";
    private static final String ERROR_UPDATE_RESPONSE = "Failed to update project. Wrong coordinates!";
    private final IProjectsDao projectsDao;
    private final IUserInteractService userInteractService;
    private final IUserHolder holder;
    private final IAuditInteractService auditInteractService;
    public ProjectService(
            IProjectsDao projectsDao,
            IUserInteractService userInteractService,
            IUserHolder holder,
            IAuditInteractService auditInteractService
    ) {
        this.projectsDao = projectsDao;
        this.userInteractService = userInteractService;
        this.holder = holder;
        this.auditInteractService = auditInteractService;
    }
    @Transactional
    @Override
    public ProjectEntity create(ProjectCreateDTO dto) {
        ProjectEntity entity = convertDTOToEntity(dto);

        List<UserRefEntity> userRefEntities = new ArrayList<>(entity.getStaff());
        userRefEntities.add(entity.getManager());
        List<UUID> uuids = new ArrayList<>();
        for (UserRefEntity userRefEntity : userRefEntities) {
            uuids.add(userRefEntity.getUuid());
        }

        userInteractService.check(uuids);
        entity = checkAndSaveEntity(entity);

      UserShortDTO userShortDTO = holder.getUser();

        String text =  String.format(PROJECT_CREATED, dto.getName());
        auditInteractService.send(userShortDTO, entity.getUuid(), text, EssenceType.PROJECT);

        return entity;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<ProjectEntity> get(PageRequest pageRequest, boolean archived) {
        UserShortDTO dto = holder.getUser();
        UUID uuid = dto.getUuid();
        if(dto.getRole().equals(UserRole.ADMIN)) {
            return findOrThrowForAdmin(pageRequest, archived);
        } else {
            return findOrThrowForUser(pageRequest, archived, uuid);
        }
    }
    @Transactional(readOnly = true)
    @Override
    public ProjectEntity get(UUID projectUuid) {
        UserShortDTO dto = holder.getUser();
        if(dto.getRole().equals(UserRole.ADMIN)) {
            return projectsDao.findById(projectUuid)
                    .orElseThrow(() -> new FindEntityException(PROJECT_NOT_EXIST_RESPONSE));
        } else {
            return projectsDao.findByUuidAndManagerUuidOrUuidAndStaffUuid(
                            projectUuid, dto.getUuid(),
                            projectUuid, dto.getUuid())
                    .orElseThrow(() -> new FindEntityException(PROJECT_NOT_EXIST_RESPONSE));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectEntity> getByUser(UUID userUuid) {
        List<ProjectEntity> entities = projectsDao.findByManagerUuidOrStaffUuid(userUuid, userUuid);
        if(entities.isEmpty()) {
            throw new FindEntityException(ERROR_GET_PAGE_BY_USER);
        }
        return entities;
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProjectEntity> get(List<UUID> projectUuids, UUID user) {
        return projectsDao.findByUuidInAndManagerUuidOrUuidInAndStaffUuid(
                projectUuids, user,
                projectUuids, user);
    }

    @Override
    public boolean ifExist(UUID project, UUID implementer) {
         return projectsDao.existsByUuidAndManagerUuidOrUuidAndStaffUuid(
                 project, implementer,
                 project, implementer);
    }
    @Transactional
    @Override
    public ProjectEntity update(ProjectCreateDTO dto, CoordinatesDTO coordinates) {
        ProjectEntity entity = projectsDao.findById(coordinates.getUuid())
                .orElseThrow(() -> new FindEntityException(PROJECT_NOT_EXIST_RESPONSE));

        if(!entity.getDtUpdate().withNano(0)
                .isEqual(coordinates.getDtUpdate().withNano(0))
        ) {
            throw new NotVerifiedCoordinatesException(ERROR_UPDATE_RESPONSE);
        }

        setFieldsToUpdate(entity, dto);

        try {
            projectsDao.saveAndFlush(entity);
        } catch (DataAccessException ex) {
            throw new UndefinedDBEntityException(ex.getMessage(), ex);
        }

        UserShortDTO userShortDTO = holder.getUser();
        String text =  String.format(PROJECT_UPDATED, dto.getName());
        auditInteractService.send(userShortDTO, entity.getUuid(),text, EssenceType.PROJECT);

        return entity;
    }

    private ProjectEntity convertDTOToEntity(ProjectCreateDTO dto) {
        List<UserRefEntity> userRefEntities = new ArrayList<>();
        for (UserRefDTO staff : dto.getStaff()) {
            userRefEntities.add(new UserRefEntity(staff.getUuid()));
        }
        ProjectEntity entity = new ProjectEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setManager(new UserRefEntity(dto.getManager().getUuid()));
        entity.setStaff(userRefEntities);
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private ProjectEntity checkAndSaveEntity(ProjectEntity entity) {
        try {
            entity = projectsDao.saveAndFlush(entity);
        } catch (DataAccessException ex) {
            if (ex.getMessage().contains(NAME_UNIQUE_CONSTRAINT)) {
                throw new DataIntegrityViolationException(PROJECT_EXIST_RESPONSE, ex);
            } else {
                throw new UndefinedDBEntityException(ex.getMessage(), ex);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException (ex.getMessage(), ex);
        }
        return entity;
    }

    private void setFieldsToUpdate(ProjectEntity entity, ProjectCreateDTO dto) {
        List<UserRefEntity> userRefEntities = new ArrayList<>();
        for (UserRefDTO staff : dto.getStaff()) {
            userRefEntities.add(new UserRefEntity(staff.getUuid()));
        }

        entity.setStaff(userRefEntities);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setManager(new UserRefEntity(dto.getManager().getUuid()));
        entity.setStatus(dto.getStatus());
    }

    private Page<ProjectEntity> findOrThrowForAdmin(PageRequest pageRequest, boolean archived) {
        try {
            if(archived) {
                return projectsDao.findAll(pageRequest);
            } else {
                return projectsDao.findByStatus(ProjectStatus.ACTIVE, pageRequest);
            }
        } catch (DataAccessException ex) {
            throw new FindEntityException(ERROR_GET_RESPONSE, ex);
        }
    }

    private Page<ProjectEntity> findOrThrowForUser(PageRequest pageRequest, boolean archived, UUID uuid) {
        try {
            if(archived) {
                return projectsDao.findByManagerUuidOrStaffUuid(uuid, uuid, pageRequest);
            } else {
                return projectsDao.findByStatusAndManagerUuidOrStatusOrStaffUuid(
                        ProjectStatus.ACTIVE,
                        uuid, ProjectStatus.ACTIVE, uuid,
                        pageRequest);
            }
        } catch (DataAccessException ex) {
            throw new FindEntityException(ERROR_GET_RESPONSE, ex);
        }
    }

}
