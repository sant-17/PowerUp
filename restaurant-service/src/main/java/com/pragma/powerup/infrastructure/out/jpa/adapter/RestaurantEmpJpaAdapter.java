package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.infrastructure.exception.UsersDoNotMatchException;
import com.pragma.powerup.infrastructure.feign.service.IFeignClientSpringService;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmpEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class RestaurantEmpJpaAdapter implements IRestaurantEmpPersistencePort {
    private final IRestaurantEmpRepository restaurantEmpRepository;
    private final IRestaurantEmpEntityMapper restaurantEmpEntityMapper;
    private final IFeignClientSpringService feignClientSpringService;
    private final RestaurantJpaAdapter restaurantJpaAdapter;

    @Override
    public RestaurantEmpModel saveRestaurantEmp(RestaurantEmpModel restaurantEmpModel) {
        String username = usernameToken();
        RestaurantModel restaurantModel = restaurantJpaAdapter.getRestaurantById(restaurantEmpModel.getRestaurant());
        UserResponseDto userResponseDto = feignClientSpringService.getUserById(restaurantModel.getOwner());

        if (!username.equals(userResponseDto.getEmail())){
            throw new UsersDoNotMatchException();
        }

        RestaurantEmpEntity restaurantEmpEntity = restaurantEmpRepository
                .save(restaurantEmpEntityMapper.toEntity(restaurantEmpModel));
        return restaurantEmpEntityMapper.toRestaurantEmpModel(restaurantEmpEntity);
    }

    public static String usernameToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails){
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }
}
