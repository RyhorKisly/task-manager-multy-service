package by.itacademy.sharedresource.core.dto;

import by.itacademy.sharedresource.core.enums.EssenceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonRootName(value = "audit")
public class AuditCreateDTO {
    @JsonProperty("user")
    private UserShortDTO user;
    @NotBlank(message = "Text is mandatory")
    private String text;
    @NotNull(message = "Type is mandatory with UpperCase")
    private EssenceType type;
    @NotBlank(message = "Id is mandatory")
    private String id;
}
