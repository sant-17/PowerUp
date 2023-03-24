package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.NoDishFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;


    @Override
    public DishModel saveDish(DishModel dishModel) {
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
        //return dishEntityMapper.toDishModelList(
        //        dishRepository.findDishesByRestaurantId(restaurant, pageable)
    }
}
