package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmpEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmpJpaAdapter implements IRestaurantEmpPersistencePort {
    private final IRestaurantEmpRepository restaurantEmpRepository;
    private final IRestaurantEmpEntityMapper restaurantEmpEntityMapper;

    @Override
    public RestaurantEmpModel saveRestaurantEmp(RestaurantEmpModel restaurantEmpModel) {
        RestaurantEmpEntity restaurantEmpEntity = restaurantEmpRepository
                .save(restaurantEmpEntityMapper.toEntity(restaurantEmpModel));
        return restaurantEmpEntityMapper.toRestaurantEmpModel(restaurantEmpEntity);
    }
}
