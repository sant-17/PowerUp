package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.DishCategoryRequestDto;
import com.pragma.powerup.application.dto.response.DishCategoryResponseDto;
import com.pragma.powerup.domain.model.DishCategoryModel;

import java.util.List;

public interface IDishCategorySpringService {

    void saveDishCategory(DishCategoryRequestDto categoryRequestDto);
    List<DishCategoryResponseDto> getAllDishCategories();
    DishCategoryResponseDto getDishCategoryById(Long id);
}
