package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
    List<RestaurantModel> getAllRestaurants();
    RestaurantModel getRestaurantById(Long id);
    List<RestaurantModel> getAllRestaurantsPaging(Integer pageNumber, Integer pageSize);
}
