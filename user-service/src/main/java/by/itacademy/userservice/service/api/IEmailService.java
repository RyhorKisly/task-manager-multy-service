package by.itacademy.userservice.service.api;

import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationTokenEntity;

public interface IEmailService {
    void sendEmail(UserEntity item, VerificationTokenEntity token);
}
