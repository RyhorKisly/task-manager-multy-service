package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.core.enums.TaskStatus;
import by.itacademy.taskservice.dao.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ITaskDao extends JpaRepository<TaskEntity, UUID> {

    Optional<TaskEntity> findByUuidAndProjectIn(
            UUID uuid, List<UUID> projects
    );

    Page<TaskEntity> findByProjectInAndStatusInAndImplementerIn(
            List<UUID> projects,
            List<TaskStatus> statuses,
            List<UUID> implementers,
            Pageable pageable
    );

    Page<TaskEntity> findByProjectIn(
            List<UUID> projects,
            Pageable pageable
    );

    Page<TaskEntity> findByImplementerIn(
            List<UUID> implementers,
            Pageable pageable
    );
}
