package by.itacademy.taskservice.dao.repositories;

import by.itacademy.taskservice.dao.entity.UserRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserDao extends JpaRepository<UserRefEntity, UUID> {
}
