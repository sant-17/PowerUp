package com.pragma.powerup.application.service.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.application.mapper.IDishUpdateRequestMapper;
import com.pragma.powerup.application.service.IDishSpringService;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DishSpringService implements IDishSpringService {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishUpdateRequestMapper dishUpdateRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toDish(dishRequestDto);
        dishModel.setActive(true);
        dishServicePort.saveDish(dishModel);
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishes());
    }

    @Override
    public DishResponseDto getDishById(Long id) {
        return dishResponseMapper.toResponse(dishServicePort.getDishById(id));
    }

    @Override
    public void updateDishById(Long id, DishUpdateRequestDto dishUpdateRequestDto) {
        DishModel dishModel = dishUpdateRequestMapper.toDish(dishUpdateRequestDto);
        dishServicePort.updateDishById(id, dishModel);
    }

    @Override
    public void setDishActive(Long id, DishUpdateRequestDto dishUpdateRequestDto) {
        DishModel dishModel = dishUpdateRequestMapper.toDish(dishUpdateRequestDto);
        dishServicePort.setDishActive(id, dishModel);
    }

    @Override
    public List<DishResponseDto> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize) {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishesPaging(restaurant, pageNumber, pageSize));
    }
}
