package com.projectX.services;

import com.projectX.configs.security.JwtProvider;
import com.projectX.dao.UserDao;
import com.projectX.dto.RegistrationRequest;
import com.projectX.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao userDao;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public String registrationUserAndCreateToken(RegistrationRequest registrationRequest) {
        String token = "";
        String login = registrationRequest.getLogin();
        String password = passwordEncoder.encode(registrationRequest.getPassword());
        int userCreationSuccessful = userDao.create(login, password);

        if (userCreationSuccessful == 1) {
            token = jwtProvider.generateToken(login);
        }

        return token;
    }
}
