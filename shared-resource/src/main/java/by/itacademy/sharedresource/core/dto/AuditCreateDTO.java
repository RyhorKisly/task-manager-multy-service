package by.itacademy.sharedresource.core.dto;

import by.itacademy.sharedresource.core.enums.EssenceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    public AuditCreateDTO() {
    }
    public AuditCreateDTO(UserShortDTO user, String text, EssenceType type, String id) {
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UserShortDTO getUserGeneralDTO() {
        return user;
    }

    public void setUserGeneralDTO(UserShortDTO user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EssenceType getType() {
        return type;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
