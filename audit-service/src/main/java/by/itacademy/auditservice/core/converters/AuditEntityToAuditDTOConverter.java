package by.itacademy.auditservice.core.converters;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.dto.UserDTO;
import by.itacademy.auditservice.core.enums.EssenceType;
import by.itacademy.auditservice.core.enums.UserRole;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;

public class AuditEntityToAuditDTOConverter implements Converter<AuditEntity, AuditDTO> {
    @Override
    public AuditDTO convert(AuditEntity entity) {
        AuditDTO auditDTO = new AuditDTO();
        UserDTO userDTO = new UserDTO();

        userDTO.setUuid(entity.getUser().getUuid());
        userDTO.setMail(entity.getUser().getMail());
        userDTO.setFio(entity.getUser().getFio());
        userDTO.setRole(UserRole.valueOf(entity.getUser().getRole()));

        auditDTO.setUuid(entity.getUuid());
        auditDTO.setDtCreate(entity.getDtCreate());
        auditDTO.setUser(userDTO);
        auditDTO.setText(entity.getText());
        auditDTO.setType(EssenceType.valueOf(entity.getType()));
        auditDTO.setId(auditDTO.getId());
;
        return auditDTO;
    }

}
