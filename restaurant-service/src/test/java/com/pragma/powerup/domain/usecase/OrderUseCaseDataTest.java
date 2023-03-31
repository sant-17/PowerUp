package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.model.RestaurantModel;

import java.time.LocalDateTime;

public class OrderUseCaseDataTest {

    public static OrderModel getOrder(){
        OrderModel orderModel = new OrderModel(
                1L,
                1L,
                LocalDateTime.now(),
                "PENDIENTE",
                new RestaurantEmpModel(
                        1L,
                        1L
                ),
                new RestaurantModel(
                        1L,
                        "Restaurante",
                        "Restaurante dir",
                        2L,
                        "573001112233",
                        333L,
                        "imagen.url"
                ),
                OrderDishesDataTest.getDishes(),
                1
        );

        return orderModel;
    }
}
