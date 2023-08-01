package by.itacademy.userservice.controllers;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.service.api.IUserAuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
public class UserAuthenticationController {
    private final IUserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(IUserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/users/registration")
    public ResponseEntity<?> register(
            @RequestBody @Valid UserRegistrationDTO userRegistrationDTO
            ) {
        userAuthenticationService.registerUser(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/verification")
    public ResponseEntity<?> verify(
            @RequestParam String code,
            @RequestParam @Email String mail
    ) {
        userAuthenticationService.verifyUser(code, mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//TODO Доделать методы
    @PostMapping("/users/login")
    public ResponseEntity<?> authorize(
            @RequestBody @Valid UserLoginDTO userLoginDTO
            ) {
        userAuthenticationService.authorizeUser(userLoginDTO);
        return new ResponseEntity<>("Logged in", HttpStatus.OK);
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> getCard() {
        return new ResponseEntity<>("Недоработан", HttpStatus.OK);
    }
}
