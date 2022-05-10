package com.projectX.services;

import com.projectX.configs.security.JwtProvider;
import com.projectX.dao.UserDAO;
import com.projectX.dto.LoginRequest;
import com.projectX.dto.RegistrationRequest;
import com.projectX.dto.UserDTO;
import com.projectX.entities.User;
import com.projectX.exceptions.user.UniqueUsernameException;
import com.projectX.exceptions.user.UserWrongCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

@Service
public class UserService {

    private UserDAO userDao;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDao, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public String registrationUserAndCreateToken(RegistrationRequest registrationRequest) throws UniqueUsernameException {
        String token = "";
        String login = registrationRequest.getLogin();
        String password = passwordEncoder.encode(registrationRequest.getPassword());
        int userCreationSuccessful = userDao.create(login, password);

        if (userCreationSuccessful == 1) {
            token = jwtProvider.generateToken(login);
        }

        return token;
    }

    public String loginUser(LoginRequest loginRequest) throws UserWrongCredentialsException {
        String token = "";
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        User user = userDao.retrieve(login);

        if (passwordEncoder.matches(password, user.getPassword())) {
            token = jwtProvider.generateToken(login);
        } else {
            throw new UserWrongCredentialsException("Login or password entered incorrectly.");
        }

        return token;
    }

    public UserDTO showInfoAboutCurrentUser(Principal principal) throws UserWrongCredentialsException {
        User user = userDao.retrieve(principal.getName());
        return new UserDTO(user);
    }
}
