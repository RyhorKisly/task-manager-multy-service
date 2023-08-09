package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.dao.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProjectsDao extends JpaRepository<ProjectEntity, UUID> {
}
