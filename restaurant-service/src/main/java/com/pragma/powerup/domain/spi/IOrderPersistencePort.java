package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderModel;

import java.util.List;

public interface IOrderPersistencePort {
    OrderModel saveOrder(OrderModel orderModel);
    List<OrderModel> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status);
    OrderModel setChef(Long id, OrderModel orderModel);
}
