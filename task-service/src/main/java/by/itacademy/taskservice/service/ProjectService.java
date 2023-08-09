package by.itacademy.taskservice.service;

import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import by.itacademy.taskservice.dao.repositories.IProjectsDao;
import by.itacademy.taskservice.service.api.IProjectService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private static final String PROJECT_EXIST_RESPONSE = "Project with this login exists";
    private static final String NAME_UNIQUE_CONSTRAINT = "projects_name_unique";
    private final IProjectsDao projectsDao;

    public ProjectService(
            IProjectsDao projectsDao
    ) {
        this.projectsDao = projectsDao;
    }

    @Override
    @Transactional
    public ProjectEntity create(ProjectCreateDTO dto) {
        ProjectEntity entity = convertDTOToEntity(dto);
        entity = checkAndSaveEntity(entity);

        return entity;
    }

    private ProjectEntity convertDTOToEntity(ProjectCreateDTO dto) {
        ProjectEntity entity = new ProjectEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setManager(dto.getManager().getUuid());
        entity.setStaff(dto.getStaff().stream().map(UserRefDTO::getUuid).toList());
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
}
