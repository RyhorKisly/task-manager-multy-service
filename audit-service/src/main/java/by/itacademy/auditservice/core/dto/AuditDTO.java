package by.itacademy.auditservice.core.dto;

import by.itacademy.sharedresource.core.dto.UserShortDTO;
import by.itacademy.sharedresource.core.enums.EssenceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private UUID uuid;
    private Long dtCreate;
    private UserShortDTO user;
    private String text;
    private EssenceType type;
    private String id;
}
