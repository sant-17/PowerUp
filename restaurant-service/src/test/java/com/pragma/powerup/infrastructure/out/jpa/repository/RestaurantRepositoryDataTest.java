package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;

import java.util.Arrays;
import java.util.List;

public class RestaurantRepositoryDataTest {

    public static RestaurantEntity getRestaurant(){
        RestaurantEntity restaurant = new RestaurantEntity(
                1L,
                "Restaurant",
                "Restaurant address",
                1L,
                "573000000000",
                333L,
                "image.url"
        );
        return restaurant;
    }

    public static List<RestaurantEntity> getRestaurantList(){
        RestaurantEntity restaurant1 = new RestaurantEntity(
                1L,
                "Restaurant",
                "Restaurant address",
                1L,
                "573000000000",
                333L,
                "image.url"
        );

        RestaurantEntity restaurant2 = new RestaurantEntity(
                2L,
                "Restaurant2",
                "Restaurant address2",
                2L,
                "573000000000",
                333L,
                "image.url"
        );

        return Arrays.asList(restaurant1, restaurant2);
    }
}
