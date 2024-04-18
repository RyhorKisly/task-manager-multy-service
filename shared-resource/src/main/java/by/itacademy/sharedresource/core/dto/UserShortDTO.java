package by.itacademy.sharedresource.core.dto;


import by.itacademy.sharedresource.core.validatiors.annotations.ValidEmail;
import by.itacademy.sharedresource.core.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserShortDTO {
    @NotNull(message = "Uuid is mandatory")
    private UUID uuid;
    @ValidEmail
    @NotBlank(message = "Email is mandatory")
    private String mail;
    @NotBlank(message = "Fio is mandatory")
    private String fio;
    @NotNull(message = "Role is mandatory")
    private UserRole role;
}
