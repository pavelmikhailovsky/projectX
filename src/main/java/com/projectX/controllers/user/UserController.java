package com.projectX.controllers.user;

import com.projectX.dto.RegistrationRequest;
import com.projectX.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrationUser(@RequestBody RegistrationRequest registrationRequest) {
        userService.registrationUser(registrationRequest);
        // TODO return jwtToken
    }

}
