package by.itacademy.userservice.dao.repositories;

import by.itacademy.userservice.dao.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IVerificationDao extends CrudRepository<VerificationToken, UUID> {
    Optional<VerificationToken> findByToken(UUID token);
}
