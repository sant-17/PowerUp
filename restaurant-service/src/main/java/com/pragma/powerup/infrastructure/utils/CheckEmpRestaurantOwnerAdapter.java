package com.pragma.powerup.infrastructure.utils;

import com.pragma.powerup.domain.exception.NoRestaurantFoundException;
import com.pragma.powerup.domain.exception.UsersDoNotMatchException;
import com.pragma.powerup.domain.spi.ICheckEmpRestaurantOwnerPort;
import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckEmpRestaurantOwnerAdapter implements ICheckEmpRestaurantOwnerPort {

    private final IRestaurantRepository restaurantRepository;
    private final IUserFeignClientService userFeignClientService;

    @Override
    public void checkOwner(Long id, String username) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id)
                .orElseThrow(NoRestaurantFoundException::new);

        UserResponseDto userResponseDto = userFeignClientService.getUserById(restaurantEntity.getOwner());

        if (!username.equals(userResponseDto.getEmail())){
            throw new UsersDoNotMatchException();
        }
    }
}
