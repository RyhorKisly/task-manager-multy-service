package by.itacademy.auditservice.core.converters;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import by.itacademy.sharedresource.core.dto.UserShortDTO;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class AuditEntityToAuditDTOConverter implements Converter<AuditEntity, AuditDTO> {
    @Override
    public AuditDTO convert(AuditEntity entity) {
        AuditDTO auditDTO = new AuditDTO();
        UserShortDTO userShortDTO = new UserShortDTO();

        userShortDTO.setUuid(entity.getUser().getUuid());
        userShortDTO.setMail(entity.getUser().getMail());
        userShortDTO.setFio(entity.getUser().getFio());
        userShortDTO.setRole(entity.getUser().getRole());

        auditDTO.setUuid(entity.getUuid());
        auditDTO.setDtCreate(entity.getDtCreate()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        auditDTO.setUser(userShortDTO);
        auditDTO.setText(entity.getText());
        auditDTO.setType(entity.getType());
        auditDTO.setId(entity.getId());

        return auditDTO;
    }

}
