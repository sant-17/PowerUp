package com.pragma.powerup.application.service.impl;

import com.pragma.powerup.application.dto.request.DishCategoryRequestDto;
import com.pragma.powerup.application.dto.response.DishCategoryResponseDto;
import com.pragma.powerup.application.mapper.IDishCategoryRequestMapper;
import com.pragma.powerup.application.mapper.IDishCategoryResponseMapper;
import com.pragma.powerup.application.service.IDishCategorySpringService;
import com.pragma.powerup.domain.api.IDishCategoryServicePort;
import com.pragma.powerup.domain.model.DishCategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DishCategorySpringService implements IDishCategorySpringService {
    private final IDishCategoryServicePort categoryServicePort;
    private final IDishCategoryRequestMapper categoryRequestMapper;
    private final IDishCategoryResponseMapper categoryResponseMapper;


    @Override
    public void saveDishCategory(DishCategoryRequestDto categoryRequestDto) {
        DishCategoryModel categoryModel = categoryRequestMapper.toDishCategory(categoryRequestDto);
        categoryServicePort.saveDishCategory(categoryModel);
    }

    @Override
    public List<DishCategoryResponseDto> getAllDishCategories() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllDishCategories());
    }

    @Override
    public DishCategoryResponseDto getDishCategoryById(Long id) {
        return categoryResponseMapper.toResponse(categoryServicePort.getDishCategoryById(id));
    }
}
