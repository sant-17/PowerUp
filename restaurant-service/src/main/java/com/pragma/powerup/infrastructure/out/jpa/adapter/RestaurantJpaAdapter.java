package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.client.IUserClientFeign;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.NoRestaurantFoundException;
import com.pragma.powerup.infrastructure.exception.NoUserFoundException;
import com.pragma.powerup.infrastructure.exception.NonAdminUserException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IUserClientFeign userClientFeign;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        UserResponseDto userResponseDto;
        try{
            userResponseDto = userClientFeign.getUserById(restaurantModel.getOwner());
        } catch (Exception ex){
            throw new NoUserFoundException();
        }
        if (!userResponseDto.getRole().getName().equals("PROPIETARIO")){
            throw new NonAdminUserException();
        }
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurantModel));
        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        if(restaurantEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantList(restaurantEntityList);
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantEntityMapper.toRestaurantModel(restaurantRepository.findById(id).
                orElseThrow(NoRestaurantFoundException::new));
    }
}
