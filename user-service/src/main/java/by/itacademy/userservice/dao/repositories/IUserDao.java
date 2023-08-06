package by.itacademy.userservice.dao.repositories;

import by.itacademy.userservice.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IUserDao extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByMail(String mail);

}
