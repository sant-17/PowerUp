package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.service.IRestaurantSpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantSpringService restaurantSpringService;

    @PostMapping("/create")
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto){

        restaurantSpringService.saveRestaurant(restaurantRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantSpringService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable("id") Long id){
        return ResponseEntity.ok(restaurantSpringService.getRestaurantById(id));
    }

    @GetMapping("/all/{size}/{number}")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(@PathVariable("number") Integer number, @PathVariable("size") Integer size){
        return ResponseEntity.ok(restaurantSpringService.getAllRestaurantsPaging(number, size));
    }
}
