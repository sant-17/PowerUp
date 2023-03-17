package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishCategoryModel;

import java.util.List;

public interface IDishCategoryServicePort {
    void saveDishCategory(DishCategoryModel categoryModel);
    List<DishCategoryModel> getAllDishCategories();
    DishCategoryModel getDishCategoryById(Long id);
}
