package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);
    List<RestaurantModel> getAllRestaurants();
    RestaurantModel getRestaurantById(Long id);
    List<RestaurantModel> getAllRestaurantsPaging(Integer pageNumber, Integer pageSize);
}
