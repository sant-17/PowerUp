package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantEmpServicePort;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.spi.ICheckEmpRestaurantOwnerPort;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.domain.spi.IUserContextPort;

public class RestaurantEmpUseCase implements IRestaurantEmpServicePort {

    private final IRestaurantEmpPersistencePort restaurantEmpPersistencePort;
    private final IUserContextPort userContextPort;
    private final ICheckEmpRestaurantOwnerPort checkEmpRestaurantOwnerPort;

    public RestaurantEmpUseCase(IRestaurantEmpPersistencePort restaurantEmpPersistencePort, IUserContextPort userContextPort, ICheckEmpRestaurantOwnerPort checkEmpRestaurantOwnerPort) {
        this.restaurantEmpPersistencePort = restaurantEmpPersistencePort;
        this.userContextPort = userContextPort;
        this.checkEmpRestaurantOwnerPort = checkEmpRestaurantOwnerPort;
    }

    @Override
    public void saveRestaurantEmp(RestaurantEmpModel restaurantEmpModel) {
        checkEmpRestaurantOwnerPort.checkOwner(restaurantEmpModel.getRestaurant(), userContextPort.getUserContext());
        restaurantEmpPersistencePort.saveRestaurantEmp(restaurantEmpModel);
    }
}
