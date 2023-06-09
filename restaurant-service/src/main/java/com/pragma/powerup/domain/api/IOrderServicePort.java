package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderModel;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(OrderModel orderModel);
    List<OrderModel> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status);
    void setChef(Long id);
    void setOrderStatusReady(Long id);
    void setOrderStatusDelivered(Long id, Integer code);
    void cancelOrder(Long id);

}
