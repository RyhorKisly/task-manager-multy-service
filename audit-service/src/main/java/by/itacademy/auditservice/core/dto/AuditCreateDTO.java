package by.itacademy.auditservice.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
@JsonRootName(value = "audit")
public class AuditCreateDTO {
    @JsonProperty("user")
    private UserShortDTO user;
    @NotBlank(message = "Text is mandatory")
    private String text;
    @NotBlank(message = "Type is mandatory with UpperCase")
    private String type;
    @NotBlank(message = "Id is mandatory")
    private String id;

    public AuditCreateDTO() {
    }
    public AuditCreateDTO(UserShortDTO user, String text, String type, String id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
