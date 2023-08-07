package by.itacademy.userservice.endponts.web;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserShortDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.service.api.IUserAuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserAuthenticationController {
    private final IUserAuthenticationService userAuthenticationService;
    private final ConversionService conversionService;

    public UserAuthenticationController(
            IUserAuthenticationService userAuthenticationService,
            ConversionService conversionService
    ) {
        this.userAuthenticationService = userAuthenticationService;
        this.conversionService = conversionService;
    }

    @PostMapping("/users/registration")
    public ResponseEntity<?> register(
            @RequestBody @Valid UserRegistrationDTO userRegistrationDTO
            ) {
        userAuthenticationService.register(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/verification")
    public ResponseEntity<?> verify(
            @RequestParam String code,
            @RequestParam @Email String mail
    ) {
        userAuthenticationService.verify(code, mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> authorize(
            @RequestBody @Valid UserLoginDTO userLoginDTO
    ) {
        return ResponseEntity.ok()
                .header("Authorization", userAuthenticationService.authorize(userLoginDTO)).build();
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getCard() {
        UserEntity userEntity = userAuthenticationService.getUser();
        UserDTO userDTO =  conversionService.convert(userEntity, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
