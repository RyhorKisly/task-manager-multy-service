package by.itacademy.userservice.service.api;

import by.itacademy.userservice.dao.entity.VerificationTokenEntity;

import java.util.UUID;

public interface IVerificationTokenService {
    VerificationTokenEntity get(UUID token);
    VerificationTokenEntity save(VerificationTokenEntity token);
    void delete(UUID token);
}
