package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.infrastructure.feign.user.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantEmpRequestDto;
import com.pragma.powerup.application.service.IRestaurantEmpSpringService;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class RestaurantEmpRestController {

    private final IRestaurantEmpSpringService restaurantEmpSpringService;
    private final IUserFeignClientService feignClientSpringService;

    @Operation(summary = "Create EMPLEADO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created with role EMPLEADO",
                    content = @Content),

            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content),

            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists with that exact email",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Void> saveRestaurantEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        Long employeeId = feignClientSpringService.createEmployee(employeeRequestDto);
        Long restaurant = employeeRequestDto.getRestaurant();
        RestaurantEmpRequestDto restaurantEmpRequestDto = new RestaurantEmpRequestDto();
        restaurantEmpRequestDto.setUser(employeeId);
        restaurantEmpRequestDto.setRestaurant(restaurant);
        restaurantEmpSpringService.saveRestaurantEmp(restaurantEmpRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
