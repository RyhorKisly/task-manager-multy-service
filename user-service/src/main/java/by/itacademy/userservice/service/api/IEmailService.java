package by.itacademy.userservice.service.api;

import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationToken;

public interface IEmailService {
    void sendEmail(UserEntity item, VerificationToken token);
}
