package com.projectX.controllers.user;

import com.projectX.dto.LoginRequest;
import com.projectX.dto.RegistrationRequest;
import com.projectX.dto.TokenReturnResponse;
import com.projectX.dto.UserDTO;
import com.projectX.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenReturnResponse> registration(@RequestBody RegistrationRequest registrationRequest) {
        String token = userService.registrationUserAndCreateToken(registrationRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TokenReturnResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenReturnResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new TokenReturnResponse(token));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> profile(Principal principal) throws IOException, NoSuchMethodException {
        UserDTO userInfo = userService.showInfoAboutCurrentUser(principal);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userInfo);
    }

}
