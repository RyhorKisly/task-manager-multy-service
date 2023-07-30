package by.itacademy.auditservice.core.converters;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.core.dto.PageDTO;
import by.itacademy.auditservice.core.dto.UserDTO;
import by.itacademy.auditservice.core.enums.EssenceType;
import by.itacademy.auditservice.core.enums.UserRole;
import by.itacademy.auditservice.dao.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageToPageDTOConverter implements Converter<Page<AuditEntity>, PageDTO<AuditDTO>> {
    @Override
    public PageDTO<AuditDTO> convert(Page<AuditEntity> page) {
        List<AuditDTO> auditDTOS = new ArrayList<>();
        for (AuditEntity entity : page.getContent()) {
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
            auditDTOS.add(auditDTO);
        }

        PageDTO<AuditDTO> pageDTO = new PageDTO<>();
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPage(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        pageDTO.setFirst(page.isFirst());
        pageDTO.setNumberOfElements(page.getNumberOfElements());
        pageDTO.setLast(page.isLast());
        pageDTO.setContent(auditDTOS);

        return pageDTO;
    }


}
