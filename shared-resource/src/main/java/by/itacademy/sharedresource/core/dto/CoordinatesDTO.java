package by.itacademy.sharedresource.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatesDTO {
    @NotBlank(message = "UUID is mandatory")
    private UUID uuid;
    @NotBlank(message = "dtUpdate is mandatory")
    private LocalDateTime dtUpdate;
}
