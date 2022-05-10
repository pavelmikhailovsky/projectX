package com.projectX.exceptions.user;

public class UserWrongCredentialsException extends Exception{

    public UserWrongCredentialsException(String message) {
        super(message);
    }
}
