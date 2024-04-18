package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.VerificationDTO;

public interface IEmailService {
    void sendEmail(UserDTO item, VerificationDTO token);
}
