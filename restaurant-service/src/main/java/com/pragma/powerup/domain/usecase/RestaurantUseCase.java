package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        if (!restaurantModel.getPhoneNumber().substring(0,1).equals("+")){
            restaurantModel.setPhoneNumber("+".concat(restaurantModel.getPhoneNumber()));
        }
        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public List<RestaurantModel> getAllRestaurantsPaging(Integer pageNumber, Integer pageSize) {
        return restaurantPersistencePort.getAllRestaurantsPaging(pageNumber, pageSize);
    }
}
