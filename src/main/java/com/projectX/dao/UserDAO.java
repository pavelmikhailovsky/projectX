package com.projectX.dao;

import com.projectX.entities.User;
import com.projectX.exceptions.user.UniqueUsernameException;
import com.projectX.exceptions.user.UserWrongCredentialsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {UniqueUsernameException.class})
    public int create(String login, String password) throws UniqueUsernameException {
        String nativeQuery = "INSERT INTO users(username, password, role) VALUES (:username, :password, 'ROLE_USER')";
        Query query = entityManager.createNativeQuery(nativeQuery)
                .setParameter("username", login)
                .setParameter("password", password);

        int i = 0;
        try {
            i = query.executeUpdate();
        } catch (Exception ex) {
            throw new UniqueUsernameException("Username is not unique.");
        }
        return i;
    }

    @Transactional
    public User retrieve(String username) throws UserWrongCredentialsException {
        String nativeQuery = "SELECT * FROM users WHERE username=:username";
        Query query = entityManager.createNativeQuery(nativeQuery, User.class)
                .setParameter("username", username);

        if (query.getResultList().size() != 1) {
            throw new UserWrongCredentialsException("Login or password entered incorrectly.");
        }

        return (User) query.getSingleResult();
    }
}
