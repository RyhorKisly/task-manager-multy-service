package by.itacademy.userservice.core.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VerificationDTO(
    UUID uuid,
    String mail,
    LocalDateTime dtCreate
) {}
