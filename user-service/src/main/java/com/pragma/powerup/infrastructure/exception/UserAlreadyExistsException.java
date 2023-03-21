package com.pragma.powerup.infrastructure.exception;

public class UserAlreadyExistsException extends RuntimeException{

    String param;
    public UserAlreadyExistsException(String email) {
        this.param = email;
    }

    public String getParam() {
        return param;
    }
}
