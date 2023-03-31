package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.OrderDishesModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDishesDataTest {
    public static List<OrderDishesModel> getDishes(){
        OrderDishesModel orderDishesModel1 = new OrderDishesModel(
                1L,
                1L,
                10
        );

        OrderDishesModel orderDishesModel2 = new OrderDishesModel(
                1L,
                2L,
                10
        );

        List<OrderDishesModel> orderDishesModelList = new ArrayList<>();
        orderDishesModelList.add(orderDishesModel1);
        orderDishesModelList.add(orderDishesModel2);

        return orderDishesModelList;
    }
}
