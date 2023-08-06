package by.itacademy.userservice.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "audit")
public class AuditSendDTO {
    @JsonProperty("user")
    private UserShortDTO user;
    private String text;
    private String type;
    private String id;

    public AuditSendDTO() {
    }
    public AuditSendDTO(UserShortDTO user, String text, String type, String id) {
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
