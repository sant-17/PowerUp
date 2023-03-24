package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    void saveDish(DishModel dishModel);
    List<DishModel> getAllDishes();
    DishModel getDishById(Long id);
    void updateDishById(Long id, DishModel dishModel);
    void setDishActive(Long id, DishModel dishModel);
    List<DishModel> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize);
}
