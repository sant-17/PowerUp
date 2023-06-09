package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishSpringService {
    void saveDish(DishRequestDto dishRequestDto);
    List<DishResponseDto> getAllDishes();
    DishResponseDto getDishById(Long id);
    void updateDishById(Long id, DishUpdateRequestDto dishUpdateRequestDto);
    void setDishActive(Long id, DishUpdateRequestDto dishUpdateRequestDto);
    List<DishResponseDto> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize);
}
