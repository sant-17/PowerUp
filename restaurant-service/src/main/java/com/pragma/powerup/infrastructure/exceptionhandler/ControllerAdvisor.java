package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.InvalidCodeException;
import com.pragma.powerup.domain.exception.InvalidPageNumberException;
import com.pragma.powerup.domain.exception.InvalidPageSizeException;
import com.pragma.powerup.domain.exception.NewOrderStatusNotValidException;
import com.pragma.powerup.domain.exception.NoRestaurantFoundException;
import com.pragma.powerup.domain.exception.OrderCantBeCancelledException;
import com.pragma.powerup.domain.exception.OrderWithWrongClientException;
import com.pragma.powerup.domain.exception.UserCantOrderException;
import com.pragma.powerup.domain.exception.UserFromDifferentRestaurantException;
import com.pragma.powerup.domain.exception.UsersDoNotMatchException;
import com.pragma.powerup.infrastructure.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidPageNumberException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageNumberException(
            InvalidPageNumberException ignoredInvalidPageNumberException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_PAGE_NUMBER.getMessage()));
    }

    @ExceptionHandler(InvalidPageSizeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageSizeException(
            InvalidPageSizeException ignoredInvalidPageSizeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_PAGE_SIZE.getMessage()));
    }

    @ExceptionHandler(NoRestaurantFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoRestaurantFoundException(
            NoRestaurantFoundException ignoredNoRestaurantFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_RESTAURANT_FOUND.getMessage()));
    }

    @ExceptionHandler(NoCategoryFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoCategoryFoundException(
            NoCategoryFoundException ignoredNoCategoryFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_CATEGORY_FOUND.getMessage()));
    }

    @ExceptionHandler(NonAdminUserException.class)
    public ResponseEntity<Map<String, String>> handleNonUserAdminException(
            NonAdminUserException ignoredNonUserAdminException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NON_ADMIN_USER.getMessage()));
    }

    @ExceptionHandler(NonOwnerUserException.class)
    public ResponseEntity<Map<String, String>> handleNonUserOwnerException(
            NonOwnerUserException ignoredNonUserOwnerException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NON_OWNER_USER.getMessage()));
    }

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoUserFoundException(
            NoUserFoundException ignoredNoUserFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_USER_FOUND.getMessage()));
    }

    @ExceptionHandler(NoDishFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDishFoundException(
            NoDishFoundException ignoredNoDishFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DISH_FOUND.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException ignoredUserAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ALREADY_EXISTS.getMessage().concat(ignoredUserAlreadyExistsException.getParam())));
    }

    @ExceptionHandler(UsersDoNotMatchException.class)
    public ResponseEntity<Map<String, String>> handleUsersDoNotMatchException(
            UsersDoNotMatchException ignoredUsersDoNotMatchException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USERS_DONT_MATCH.getMessage()));
    }

    @ExceptionHandler(UserCantOrderException.class)
    public ResponseEntity<Map<String, String>> handleUserCantOrderException(
            UserCantOrderException ignoredUserCantOrderException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_CANT_ORDER.getMessage()));
    }

    @ExceptionHandler(UserFromDifferentRestaurantException.class)
    public ResponseEntity<Map<String, String>> handleUserCantOrderException(
            UserFromDifferentRestaurantException ignoredUserDifferentRestaurantException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_WRONG_RESTAURANT.getMessage()));
    }

    @ExceptionHandler(NoOrderFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoOrderFoundException(
            NoOrderFoundException ignoredNoOrderFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_ORDER_FOUND.getMessage()));
    }

    @ExceptionHandler(NewOrderStatusNotValidException.class)
    public ResponseEntity<Map<String, String>> handleNewOrderStatusNotValidException(
            NewOrderStatusNotValidException ignoredNewOrderStatusNotValidException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NEW_STATUS_INVALID.getMessage()));
    }

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCodeExceptionException(
            InvalidCodeException ignoredInvalidCodeExceptionException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.WRONG_CODE_ORDER.getMessage()));
    }

    @ExceptionHandler(OrderCantBeCancelledException.class)
    public ResponseEntity<Map<String, String>> handleOrderCantBeCancelledException(
            OrderCantBeCancelledException ignoredOrderCantBeCancelledException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CANT_CANCEL_ORDER.getMessage()));
    }

    @ExceptionHandler(OrderWithWrongClientException.class)
    public ResponseEntity<Map<String, String>> handleOrderWithWrongClientException(
            OrderWithWrongClientException ignoredOrderWithWrongClientException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.WRONG_ORDER_CLIENT.getMessage()));
    }

    @ExceptionHandler(DishFromDifferentRestaurantException.class)
    public ResponseEntity<Map<String, String>> handleDishFromDifferentRestaurantException(
            DishFromDifferentRestaurantException ignoredDishFromDifferentRestaurantException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.WRONG_DISH_ORDER.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
}