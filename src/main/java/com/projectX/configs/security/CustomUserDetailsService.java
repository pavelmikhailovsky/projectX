package com.projectX.configs.security;

import com.projectX.dao.UserDAO;
import com.projectX.entities.User;
import com.projectX.exceptions.user.UserWrongCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserDAO userDao;

    public CustomUserDetailsService(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.retrieve(username);
        } catch (UserWrongCredentialsException e) {
            throw new RuntimeException(e);
        }
        return SecurityUser.fromUser(user);
    }
}
