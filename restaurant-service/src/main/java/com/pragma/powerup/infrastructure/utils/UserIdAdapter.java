package com.pragma.powerup.infrastructure.utils;

import com.pragma.powerup.domain.spi.IUserIdPort;
import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserIdAdapter implements IUserIdPort {
    private final IUserFeignClientService feignClientService;
    @Override
    public Long getUserIdByEmail(String email) {
        UserResponseDto userResponseDto = feignClientService.getUserByEmail(email);
        return userResponseDto.getId();
    }
}
