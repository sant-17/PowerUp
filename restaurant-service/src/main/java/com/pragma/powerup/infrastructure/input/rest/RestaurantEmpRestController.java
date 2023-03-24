package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantEmpRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.service.IRestaurantEmpSpringService;
import com.pragma.powerup.infrastructure.client.IUserClientFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurant-employee")
@RequiredArgsConstructor
public class RestaurantEmpRestController {

    private final IRestaurantEmpSpringService restaurantEmpSpringService;
    private final IUserClientFeign userClientFeign;

    @PostMapping("/create")
    public ResponseEntity<Void> saveRestaurantEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        Long restaurante = employeeRequestDto.getRestaurant();
        userClientFeign.saveUserAsEmployee(employeeRequestDto);
        UserResponseDto userResponseDto = userClientFeign.getUserByEmail(employeeRequestDto.getEmail());
        RestaurantEmpRequestDto restaurantEmpRequestDto = new RestaurantEmpRequestDto();
        restaurantEmpRequestDto.setUser(userResponseDto.getId());
        restaurantEmpRequestDto.setRestaurant(employeeRequestDto.getRestaurant());
        restaurantEmpSpringService.saveRestaurantEmp(restaurantEmpRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
