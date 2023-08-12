package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProjectsDao extends JpaRepository<ProjectEntity, UUID> {
    Page<ProjectEntity> findByStatus(ProjectStatus status, Pageable pageable);
}
