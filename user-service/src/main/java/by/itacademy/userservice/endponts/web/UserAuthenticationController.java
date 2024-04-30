package by.itacademy.userservice.endponts.web;

import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.UserLoginDTO;
import by.itacademy.userservice.core.dto.UserRegistrationDTO;
import by.itacademy.userservice.endponts.web.api.UserAuthenticationControllerApi;
import by.itacademy.userservice.service.api.IUserAuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class UserAuthenticationController implements UserAuthenticationControllerApi {
    private final IUserAuthenticationService userAuthenticationService;

    @Override
    public ResponseEntity<Void> register(UserRegistrationDTO userRegistrationDTO) {
        userAuthenticationService.register(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> verify(String code, String mail) {
        userAuthenticationService.verify(code, mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> authorize(UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok()
                .header("Authorization", userAuthenticationService.authorize(userLoginDTO)).build();
    }

    @Override
    public ResponseEntity<UserDTO> getCard() {
        UserDTO userDTO = userAuthenticationService.getUser();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
