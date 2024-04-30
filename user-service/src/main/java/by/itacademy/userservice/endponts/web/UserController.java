package by.itacademy.userservice.endponts.web;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.*;
import by.itacademy.userservice.endponts.web.api.UserControllerApi;
import by.itacademy.userservice.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerApi {
    private final IUserService userService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<UserDTO> save(UserCreateDTO userCreateDTO) {
        UserDTO userDTO = userService.createByUser(userCreateDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<UserDTO>> getPages(Integer page, Integer size) {
        Page<UserDTO> pageOfUsers =  userService.get(PageRequest.of(page, size));
        return new ResponseEntity<>(pageOfUsers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getCard(UUID uuid) {
        UserDTO userDTO = conversionService.convert(userService.get(uuid), UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> update(UUID uuid, LocalDateTime dtUpdate, UserCreateDTO userCreateDTO) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setUuid(uuid);
        coordinatesDTO.setDtUpdate(dtUpdate);
        UserDTO userDTO = userService.update(userCreateDTO, coordinatesDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> validate(List<UUID> uuids) {
        userService.validate(uuids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
