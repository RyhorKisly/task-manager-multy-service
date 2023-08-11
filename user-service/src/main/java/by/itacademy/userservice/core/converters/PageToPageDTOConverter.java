package by.itacademy.userservice.core.converters;

import by.itacademy.sharedresource.core.dto.PageDTO;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PageToPageDTOConverter implements Converter<Page<UserEntity>, PageDTO<UserDTO>> {
    @Override
    public PageDTO<UserDTO> convert(Page<UserEntity> page) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity entity : page.getContent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUuid(entity.getUuid());
            userDTO.setDtCreate(entity.getDtCreate()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());
            userDTO.setDtUpdate(entity.getDtUpdate()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());
            userDTO.setMail(entity.getMail());
            userDTO.setFio(entity.getFio());
            userDTO.setRole(entity.getRole());
            userDTO.setStatus(entity.getStatus());
            userDTOS.add(userDTO);
        }

        PageDTO<UserDTO> pageDTO = new PageDTO<>();
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPage(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        pageDTO.setFirst(page.isFirst());
        pageDTO.setNumberOfElements(page.getNumberOfElements());
        pageDTO.setLast(page.isLast());
        pageDTO.setContent(userDTOS);

        return pageDTO;
    }


}
