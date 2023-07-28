package by.itacademy.userservice.controllers;

import by.itacademy.userservice.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.PageDTO;
import by.itacademy.userservice.core.dto.UserCreateDTO;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.service.api.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final ConversionService conversionService;
    public UserController(
            IUserService userService,
            ConversionService conversionService
    ) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid UserCreateDTO userCreateDTO
    ) {
        userService.save(userCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageDTO> getPages(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "20") @PositiveOrZero Integer size
    ) {
        Page<UserEntity> pageOfUsers =  userService.get(PageRequest.of(page, size));
        List<UserEntity> userEntities = pageOfUsers.getContent();

        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDTOS.add(conversionService.convert(userEntity, UserDTO.class));
        }

        PageDTO pageDTO = convertPagetoPageDTO(pageOfUsers, userDTOS);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }
 
      @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getCard(
            @PathVariable UUID uuid
    ) {
        UserDTO userDTO = conversionService.convert(userService.get(uuid), UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") LocalDateTime dtUpdate,
            @RequestBody @Valid UserCreateDTO userCreateDTO
    ) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setUuid(uuid);
        coordinatesDTO.setDtUpdate(dtUpdate);
        userService.update(userCreateDTO, coordinatesDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private PageDTO convertPagetoPageDTO(
            Page<UserEntity> pageOfUsers,
            List<UserDTO> userDTOS
    ) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setNumber(pageOfUsers.getNumber());
        pageDTO.setSize(pageOfUsers.getSize());
        pageDTO.setTotalPage(pageOfUsers.getTotalPages());
        pageDTO.setTotalElements(pageOfUsers.getTotalElements());
        pageDTO.setFirst(pageOfUsers.isFirst());
        pageDTO.setNumberOfElements(pageOfUsers.getNumberOfElements());
        pageDTO.setLast(pageOfUsers.isLast());
        pageDTO.setContent(userDTOS);

        return pageDTO;
    }
}
