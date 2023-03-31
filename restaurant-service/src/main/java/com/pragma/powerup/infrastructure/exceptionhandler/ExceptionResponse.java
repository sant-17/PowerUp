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
    NO_DISH_FOUND("No dish found for the requested ID"),
    USER_WRONG_RESTAURANT("User logged it's from a different restaurant"),
    WRONG_CODE_ORDER("Code requested it's wrong"),
    CANT_CANCEL_ORDER("Order can't be cancelled, contact the restaurant"),
    WRONG_ORDER_CLIENT("Client logged must be the same client from the order"),
    WRONG_DISH_ORDER("Dish requested must be from the same order's restaurant"),
    INVALID_PAGE_NUMBER("Minimum page number its 0"),
    INVALID_PAGE_SIZE("Minimum page size its 1"),
    NEW_STATUS_INVALID("The new status it's invalid for this order. Check flowchart");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}