package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IProjectsDao extends JpaRepository<ProjectEntity, UUID> {
    Page<ProjectEntity> findByStatus(ProjectStatus status, Pageable pageable);
     boolean existsByUuidAndStaffUuidOrManagerUuid(UUID project, UUID staff, UUID manager);
}
