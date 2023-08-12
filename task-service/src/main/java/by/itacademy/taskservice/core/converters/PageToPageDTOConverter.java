package by.itacademy.taskservice.core.converters;

import by.itacademy.sharedresource.core.dto.PageDTO;
import by.itacademy.sharedresource.core.dto.UserRefDTO;
import by.itacademy.taskservice.core.dto.ProjectDTO;
import by.itacademy.taskservice.dao.entity.ProjectEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PageToPageDTOConverter implements Converter<Page<ProjectEntity>, PageDTO<ProjectDTO>> {
    @Override
    public PageDTO<ProjectDTO> convert(Page<ProjectEntity> page) {
        PageDTO<ProjectDTO> pageDTO = new PageDTO<>();
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPage(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        pageDTO.setFirst(page.isFirst());
        pageDTO.setNumberOfElements(page.getNumberOfElements());
        pageDTO.setLast(page.isLast());
        pageDTO.setContent(convertListEntitiesToListDTOS(page));

        return pageDTO;
    }

    private List<ProjectDTO> convertListEntitiesToListDTOS(Page<ProjectEntity> page) {
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (ProjectEntity entity : page.getContent()) {
            List<UserRefDTO> userRefDTOS = new ArrayList<>();
            for (UUID staff : entity.getStaff()) {
                userRefDTOS.add(new UserRefDTO(staff));
            }
            ProjectDTO dto = new ProjectDTO();
            dto.setUuid(entity.getUuid());
            dto.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            dto.setDtUpdate(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setManager(new UserRefDTO(entity.getUuid()));
            dto.setStaff(userRefDTOS);
            dto.setStatus(entity.getStatus());

            projectDTOS.add(dto);
        }
        return projectDTOS;
    }
}
