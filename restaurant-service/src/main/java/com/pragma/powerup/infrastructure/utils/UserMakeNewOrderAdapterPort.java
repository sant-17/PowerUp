package com.pragma.powerup.infrastructure.utils;

import com.pragma.powerup.domain.spi.ICheckUserCanOrderPort;
import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMakeNewOrderAdapterPort implements ICheckUserCanOrderPort {
    private final IOrderRepository orderRepository;
    private final IUserFeignClientService feignClientSpringService;
    @Override
    public Boolean canUserMakeNewOrder(String username) {
        UserResponseDto userResponseDto = feignClientSpringService.getUserByEmail(username);
        Boolean condition = orderRepository.orderByClientInProcess(
                userResponseDto.getId(), "PENDIENTE", "EN_PREPARACION", "LISTO");
        return !condition;
    }
}
