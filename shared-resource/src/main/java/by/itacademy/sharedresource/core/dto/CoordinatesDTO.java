package by.itacademy.sharedresource.core.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public class CoordinatesDTO {
    @NotBlank(message = "UUID is mandatory")
    private UUID uuid;
    @NotBlank(message = "dtUpdate is mandatory")
    private LocalDateTime dtUpdate;

    public CoordinatesDTO() {
    }

    public CoordinatesDTO(UUID uuid, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}
