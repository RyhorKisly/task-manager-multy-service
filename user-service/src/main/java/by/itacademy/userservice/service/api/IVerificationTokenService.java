package by.itacademy.userservice.service.api;

import by.itacademy.userservice.dao.entity.VerificationToken;

import java.util.UUID;

public interface IVerificationTokenService {
    VerificationToken get(UUID token);
    VerificationToken save(VerificationToken token);
    void delete(UUID token);
}
