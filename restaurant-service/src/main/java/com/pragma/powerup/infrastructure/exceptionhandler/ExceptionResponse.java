package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    NO_RESTAURANT_FOUND("No restaurant found for the requested ID"),
    NO_CATEGORY_FOUND("No restaurant found for the requested ID"),
    NON_ADMIN_USER("Owner id requested is not an Administrator"),
    NON_OWNER_USER("Owner id requested is not an Administrator"),
    NO_USER_FOUND("No User found for the requested ID (owner)"),
    USER_ALREADY_EXISTS("User already exits with requested email = "),
    USERS_DONT_MATCH("Requested user don't match with JWT username"),
    USER_CANT_ORDER("The current user has some unfinished order"),
    NO_ORDER_FOUND("No order found for requested id"),
    NO_DISH_FOUND("No dish found for the requested ID");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}