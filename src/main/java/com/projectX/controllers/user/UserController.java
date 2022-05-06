package com.projectX.controllers.user;

import com.projectX.dto.RegistrationRequest;
import com.projectX.dto.TokenReturn;
import com.projectX.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenReturn> registrationUser(@RequestBody RegistrationRequest registrationRequest) {
        String token = userService.registrationUserAndCreateToken(registrationRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TokenReturn(token));
    }

}
