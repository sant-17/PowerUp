package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void saveOrder(OrderModel orderModel) {
        orderPersistencePort.saveOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status) {
        return orderPersistencePort.getOrdersByStatus(pageNumber, pageSize, status);
    }

    @Override
    public void setChef(Long id, OrderModel orderModel) {
        orderPersistencePort.setChef(id, orderModel);
    }
}
