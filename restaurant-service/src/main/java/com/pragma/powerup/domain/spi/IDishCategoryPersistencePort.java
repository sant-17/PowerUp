package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishCategoryModel;

import java.util.List;

public interface IDishCategoryPersistencePort {
    DishCategoryModel saveDishCategory(DishCategoryModel categoryModel);
    List<DishCategoryModel> getAllDishCategories();
    DishCategoryModel getDishCategoryById(Long id);
}
