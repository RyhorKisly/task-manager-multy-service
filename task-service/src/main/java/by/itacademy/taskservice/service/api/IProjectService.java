package by.itacademy.taskservice.service.api;

import by.itacademy.taskservice.core.dto.ProjectCreateDTO;
import by.itacademy.taskservice.dao.entity.ProjectEntity;

public interface IProjectService {
    ProjectEntity create(ProjectCreateDTO dto);
}
