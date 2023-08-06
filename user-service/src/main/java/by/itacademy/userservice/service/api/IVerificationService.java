package by.itacademy.userservice.service.api;

import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IVerificationService {
    VerificationEntity get(String mail);
    VerificationEntity save(UserEntity userEntity);
    void delete(UUID token);
    void deleteByDtCreateLessThan(LocalDateTime dateTime);
}
