package com.pragma.powerup.application.service.impl;

import com.pragma.powerup.application.dto.request.RestaurantEmpRequestDto;
import com.pragma.powerup.application.mapper.IRestaurantEmpRequestMapper;
import com.pragma.powerup.application.service.IRestaurantEmpSpringService;
import com.pragma.powerup.domain.api.IRestaurantEmpServicePort;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RestaurantEmpSpringService implements IRestaurantEmpSpringService {
    private final IRestaurantEmpServicePort restaurantEmpServicePort;
    private final IRestaurantEmpRequestMapper restaurantEmpRequestMapper;

    @Override
    public void saveRestaurantEmp(RestaurantEmpRequestDto restaurantEmpRequestDto) {
        RestaurantEmpModel restaurantEmpModel = restaurantEmpRequestMapper.toRestaurantEmp(restaurantEmpRequestDto);
        restaurantEmpServicePort.saveRestaurantEmp(restaurantEmpModel);
    }
}
