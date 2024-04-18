package by.itacademy.userservice.endponts.web;

import by.itacademy.sharedresource.core.dto.CoordinatesDTO;
import by.itacademy.userservice.core.dto.*;
import by.itacademy.userservice.service.api.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody @Valid UserCreateDTO userCreateDTO
    ) {
        userService.createByUser(userCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getPages(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "20") @PositiveOrZero Integer size
    ) {
        Page<UserDTO> pageOfUsers =  userService.get(PageRequest.of(page, size));
        return new ResponseEntity<>(pageOfUsers, HttpStatus.OK);
    }
 
      @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getCard(
            @PathVariable UUID uuid
    ) {
        UserDTO userDTO = conversionService.convert(userService.get(uuid), UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<Void> update(
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

    @PostMapping("/validation")
    public ResponseEntity<Void> validate(
            @RequestBody List<UUID> uuids
            ) {
        userService.validate(uuids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
