package by.itacademy.userservice.service.api;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.VerificationDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IVerificationService {
    VerificationDTO get(String mail);
    VerificationDTO save(UserDTO userDTO);
    void delete(UUID token);
    void deleteByDtCreateLessThan(LocalDateTime dateTime);
}
