package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.NoDishFoundException;
import com.pragma.powerup.infrastructure.exception.UsersDoNotMatchException;
import com.pragma.powerup.infrastructure.feign.service.IFeignClientSpringService;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IFeignClientSpringService feignClientSpringService;
    private final RestaurantJpaAdapter restaurantJpaAdapter;


    @Override
    public DishModel saveDish(DishModel dishModel) {
        String username = usernameToken();
        RestaurantModel restaurantModel = restaurantJpaAdapter.getRestaurantById(dishModel.getRestaurant().getId());
        UserResponseDto userResponseDto = feignClientSpringService.getUserById(restaurantModel.getOwner());

        if (!username.equals(userResponseDto.getEmail())){
            throw new UsersDoNotMatchException();
        }
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dishModel));
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll();
        if (dishEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishModelList(dishEntityList);
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishEntityMapper.toDishModel(dishRepository.findById(id).
                orElseThrow(NoDishFoundException::new));
    }

    @Override
    public DishModel updateDishById(Long id, DishModel dishModel) {
        String username = usernameToken();
        RestaurantModel restaurantModel = restaurantJpaAdapter.getRestaurantById(id);
        UserResponseDto userResponseDto = feignClientSpringService.getUserById(restaurantModel.getOwner());

        if (!username.equals(userResponseDto.getEmail())){
            throw new UsersDoNotMatchException();
        }

        DishModel dishModelNew = dishEntityMapper.toDishModel(dishRepository.findById(id)
                .orElseThrow(NoDishFoundException::new));

        if (dishModel.getPrice() != null){
            dishModelNew.setPrice(dishModel.getPrice());
        }
        if (dishModel.getDescription() != null){
            dishModelNew.setDescription(dishModel.getDescription());
        }
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dishModelNew));
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public DishModel setDishActive(Long id, DishModel dishModel) {
        String username = usernameToken();
        RestaurantModel restaurantModel = restaurantJpaAdapter.getRestaurantById(id);
        UserResponseDto userResponseDto = feignClientSpringService.getUserById(restaurantModel.getOwner());

        if (!username.equals(userResponseDto.getEmail())){
            throw new UsersDoNotMatchException();
        }

        DishModel dishModelNew = dishEntityMapper.toDishModel(dishRepository.findById(id)
                .orElseThrow(NoDishFoundException::new));
        if (dishModel.getActive() != null){
            dishModelNew.setActive(dishModel.getActive());
        }
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dishModelNew));
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public List<DishModel> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by( "category"));

        return dishRepository.findAllByRestaurantId(restaurant, pageable)
                .stream()
                .map(dishEntityMapper::toDishModel)
                .collect(Collectors.toList());
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
