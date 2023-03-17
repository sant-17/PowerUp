package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishCategoryRequestDto;
import com.pragma.powerup.application.dto.response.DishCategoryResponseDto;
import com.pragma.powerup.application.service.IDishCategorySpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dish_category")
@RequiredArgsConstructor
public class DishCategoryRestController {
    private final IDishCategorySpringService categorySpringService;

    @PostMapping("/")
    public ResponseEntity<Void> saveDishCategory(@Valid @RequestBody DishCategoryRequestDto categoryRequestDto){
        categorySpringService.saveDishCategory(categoryRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DishCategoryResponseDto>> getAllDishCategories(){
        return ResponseEntity.ok(categorySpringService.getAllDishCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishCategoryResponseDto> getDishCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(categorySpringService.getDishCategoryById(id));
    }
}
