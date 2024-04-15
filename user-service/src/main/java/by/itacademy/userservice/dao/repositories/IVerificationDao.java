package by.itacademy.userservice.dao.repositories;

import by.itacademy.userservice.dao.entity.VerificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IVerificationDao extends CrudRepository<VerificationEntity, UUID> {
    Optional<VerificationEntity> findByMail(String mail);
    void deleteByDtCreateLessThan(LocalDateTime dateTime);
}
