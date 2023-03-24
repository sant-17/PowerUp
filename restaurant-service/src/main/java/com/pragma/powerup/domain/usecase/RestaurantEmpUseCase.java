package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantEmpServicePort;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;

public class RestaurantEmpUseCase implements IRestaurantEmpServicePort {

    private final IRestaurantEmpPersistencePort restaurantEmpPersistencePort;

    public RestaurantEmpUseCase(IRestaurantEmpPersistencePort restaurantEmpPersistencePort) {
        this.restaurantEmpPersistencePort = restaurantEmpPersistencePort;
    }

    @Override
    public void saveRestaurantEmp(RestaurantEmpModel restaurantEmpModel) {
        restaurantEmpPersistencePort.saveRestaurantEmp(restaurantEmpModel);
    }
}
