package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantSpringService {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);
    List<RestaurantResponseDto> getAllRestaurants();
    RestaurantResponseDto getRestaurantById(Long id);
    List<RestaurantResponseDto> getAllRestaurantsPaging(Integer pageNumber, Integer pageSize);
}
