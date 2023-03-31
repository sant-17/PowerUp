package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishCategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;

import java.util.Arrays;
import java.util.List;

public class DishRepositoryDataTest {

    public static List<DishEntity> getDishes(){
        RestaurantEntity restaurant = RestaurantRepositoryDataTest.getRestaurant();



        DishEntity dishEntity1 = new DishEntity(
                1L,
                "Pollo frito",
                "Pollo",
                499,
                "img.png",
                true,
                restaurant,
                new DishCategoryEntity(1L, "Carne", "Todo tipo")
        );

        DishEntity dishEntity2 = new DishEntity(
                2L,
                "Jugo de mora",
                "Jugo",
                399,
                "img.png",
                true,
                restaurant,
                new DishCategoryEntity(2L, "Bebida", "Todo tipo")
        );

        DishEntity dishEntity3 = new DishEntity(
                3L,
                "Jugo de mora",
                "Jugo",
                399,
                "img.png",
                true,
                restaurant,
                new DishCategoryEntity(1L, "Carne", "Todo tipo")
        );

        List<DishEntity> dishEntities = Arrays.asList(dishEntity1, dishEntity2, dishEntity3);

        return dishEntities;
    }
}
