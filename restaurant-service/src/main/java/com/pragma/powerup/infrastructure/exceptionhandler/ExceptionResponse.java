package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    NO_RESTAURANT_FOUND("No restaurant found for the requested ID"),
    NO_CATEGORY_FOUND("No restaurant found for the requested ID"),
    NON_ADMIN_USER("Owner id requested is not an Administrator"),
    NO_USER_FOUND("No User found for the requested ID (owner)"),
    USER_ALREADY_EXISTS("User already exits with requested email = "),
    NO_DISH_FOUND("No dish found for the requested ID");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}