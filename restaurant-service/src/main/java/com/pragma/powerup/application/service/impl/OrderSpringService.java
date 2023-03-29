package com.pragma.powerup.application.service.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.OrderUpdateRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.application.mapper.IOrderUpdateRequestMapper;
import com.pragma.powerup.application.service.IOrderSpringService;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderSpringService implements IOrderSpringService {

    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderServicePort orderServicePort;
    private final IOrderResponseMapper orderResponseMapper;
    private final IOrderUpdateRequestMapper orderUpdateRequestMapper;

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {
        OrderModel orderModel = orderRequestMapper.toOrder(orderRequestDto);
        orderModel.setDate(LocalDateTime.now());
        orderModel.setStatus("PENDIENTE");
        orderServicePort.saveOrder(orderModel);
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status) {
        return orderResponseMapper.toOrderList(
                orderServicePort.getOrdersByStatus(pageNumber, pageSize, status)
        );
    }

    @Override
    public void setChef(Long id, OrderUpdateRequestDto orderUpdateRequestDto) {
        OrderModel orderModel = orderUpdateRequestMapper.toOrder(orderUpdateRequestDto);
        orderServicePort.setChef(id, orderModel);

    }

    @Override
    public void setOrderStatusReady(Long id) {
        orderServicePort.setOrderStatusReady(id);
    }

    @Override
    public void setOrderStatusDelivered(Long id) {
        orderServicePort.setOrderStatusDelivered(id);
    }

    @Override
    public void cancelOrder(Long id) {
        orderServicePort.cancelOrder(id);
    }
}
