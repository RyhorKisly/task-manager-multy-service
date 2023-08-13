package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ITaskDao extends JpaRepository<TaskEntity, UUID> {
}
