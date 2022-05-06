package com.projectX.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public int create(String login, String password) {
        String nativeQuery = "INSERT INTO users(username, password, role) VALUES (:username, :password, 'ROLE_USER')";
        Query query = entityManager.createNativeQuery(nativeQuery)
                .setParameter("username", login)
                .setParameter("password", password);
        return query.executeUpdate();
    }

//    public User retrieve(String username) {
//        return
//    }

    public void delete(String username) {

    }

}
