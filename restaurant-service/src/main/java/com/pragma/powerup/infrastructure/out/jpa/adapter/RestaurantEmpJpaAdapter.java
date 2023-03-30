package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.exception.NoRestaurantFoundException;
import com.pragma.powerup.infrastructure.exception.NoUserFoundException;
import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.domain.exception.UsersDoNotMatchException;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmpEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmpRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class RestaurantEmpJpaAdapter implements IRestaurantEmpPersistencePort {
    private final IRestaurantEmpRepository restaurantEmpRepository;
    private final IRestaurantEmpEntityMapper restaurantEmpEntityMapper;

    @Override
    public RestaurantEmpModel saveRestaurantEmp(RestaurantEmpModel restaurantEmpModel) {
        RestaurantEmpEntity restaurantEmpEntity = restaurantEmpRepository
                .save(restaurantEmpEntityMapper.toEntity(restaurantEmpModel));
        return restaurantEmpEntityMapper.toRestaurantEmpModel(restaurantEmpEntity);
    }

    @Override
    public RestaurantEmpModel getEmployeeById(Long id) {
        return restaurantEmpEntityMapper.toRestaurantEmpModel(restaurantEmpRepository.findById(id)
                .orElseThrow(NoUserFoundException::new));
    }
}
