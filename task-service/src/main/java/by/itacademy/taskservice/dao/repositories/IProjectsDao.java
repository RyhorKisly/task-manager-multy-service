package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.core.enums.ProjectStatus;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IProjectsDao extends JpaRepository<ProjectEntity, UUID> {

    Page<ProjectEntity> findByStatus(ProjectStatus status, Pageable pageable);

     boolean existsByUuidAndManagerUuidOrUuidAndStaffUuid(
             UUID project, UUID manager,
             UUID project2, UUID staff);

    Optional<ProjectEntity> findByUuidAndManagerUuidOrUuidAndStaffUuid(
            UUID project, UUID manager,
            UUID project2, UUID staff);

    List<ProjectEntity> findByUuidInAndManagerUuidOrUuidInAndStaffUuid(
            List<UUID> projects, UUID manager,
            List<UUID> project2, UUID staff);

    Page<ProjectEntity> findByManagerUuidOrStaffUuid(
            UUID manager, UUID staff,
            Pageable pageable);

    List<ProjectEntity> findByManagerUuidOrStaffUuid(
            UUID manager, UUID staff);

    Page<ProjectEntity> findByStatusAndManagerUuidOrStatusOrStaffUuid(
            ProjectStatus status, UUID manager,
            ProjectStatus statusN, UUID staff,
            Pageable pageable
    );
}
