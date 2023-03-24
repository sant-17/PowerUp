package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishCategoryRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishCategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.service.IDishSpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishSpringService dishSpringService;

    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto){
        dishSpringService.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DishResponseDto>> getAllDishes(){
        return ResponseEntity.ok(dishSpringService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable("id") Long id){
        return ResponseEntity.ok(dishSpringService.getDishById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDishById(@PathVariable("id") Long id,@Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto){
        dishSpringService.updateDishById(id, dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> setDishActive(@PathVariable("id") Long id,@Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto){
        dishSpringService.setDishActive(id, dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{restaurant}/{size}/{number}")
    public ResponseEntity<List<DishResponseDto>> getAllDishesPaging(
            @PathVariable("restaurant") Long restaurant,
            @PathVariable("number") Integer pageNumber,
            @PathVariable("size") Integer pageSize){
        return ResponseEntity.ok(dishSpringService.getAllDishesPaging(restaurant, pageNumber, pageSize));
    }
}
