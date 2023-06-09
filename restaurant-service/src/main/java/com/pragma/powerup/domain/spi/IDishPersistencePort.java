package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dishModel);
    List<DishModel> getAllDishes();
    DishModel getDishById(Long id);
    DishModel updateDishById(Long id, DishModel dishModel);
    DishModel setDishActive(Long id, DishModel dishModel);
    List<DishModel> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize);
}
