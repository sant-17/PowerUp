package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.OrderUpdateRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderSpringService {
    void saveOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status);
    void setChef(Long id);
    void setOrderStatusReady(Long id);
    void setOrderStatusDelivered(Long id, Integer code);
    void cancelOrder(Long id);
}
