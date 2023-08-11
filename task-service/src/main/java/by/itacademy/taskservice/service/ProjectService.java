package by.itacademy.taskservice.service;

import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.core.exceptions.UndefinedDBEntityException;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import by.itacademy.taskservice.dao.repositories.IProjectsDao;
import by.itacademy.taskservice.endpoints.utils.JwtTokenHandler;
import by.itacademy.taskservice.service.api.IAuditInteractService;
import by.itacademy.taskservice.service.api.IProjectService;
import by.itacademy.taskservice.service.api.IUserInteractService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final IProjectsDao projectsDao;
    private final IUserInteractService userInteractService;
    private final JwtTokenHandler jwtHandler;
    private final IAuditInteractService auditInteractService;
    public ProjectService(
            IProjectsDao projectsDao,
            IUserInteractService userInteractService,
            JwtTokenHandler jwtHandler,
            IAuditInteractService auditInteractService
    ) {
        this.projectsDao = projectsDao;
        this.userInteractService = userInteractService;
        this.jwtHandler = jwtHandler;
        this.auditInteractService = auditInteractService;
    }

    @Override
    @Transactional
    public ProjectEntity create(ProjectCreateDTO dto, String bearerToken) {
        ProjectEntity entity = convertDTOToEntity(dto);
        String token = bearerToken.split(" ")[1].trim();

        List<UUID> uuids = new ArrayList<>(entity.getStaff());
        uuids.add(entity.getManager());

        //TODO  1. объединять отправку проверки участников проекта и получение юзера для отправки аудита или нет
        //      2. оставить раздельно два хождения на user-service

        userInteractService.check(uuids);
        entity = checkAndSaveEntity(entity);

        UserShortDTO userShortDTO = userInteractService.sendAndGet(token);

        //TODO  3. или может лучше использовать информацию из токена (Я в него вложил UserShortDTO)
//        UserShortDTO userShortDTO = jwtHandler.getUser(token);

        String text =  String.format(PROJECT_CREATED, dto.getName());
        auditInteractService.send(userShortDTO, entity.getUuid(),text);

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
