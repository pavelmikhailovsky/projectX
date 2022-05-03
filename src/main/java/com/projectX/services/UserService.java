package com.projectX.services;

import com.projectX.dao.UserDao;
import com.projectX.dto.RegistrationRequest;
import com.projectX.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUsername(String username) {
        return (User) userDao.retrieve(username);
    }

    public void registrationUser(RegistrationRequest registrationRequest) {
        userDao.create(registrationRequest);
    }
}
