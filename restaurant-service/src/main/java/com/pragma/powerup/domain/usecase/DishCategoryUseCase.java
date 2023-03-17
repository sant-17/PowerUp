package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishCategoryServicePort;
import com.pragma.powerup.domain.model.DishCategoryModel;
import com.pragma.powerup.domain.spi.IDishCategoryPersistencePort;

import java.util.List;

public class DishCategoryUseCase implements IDishCategoryServicePort {
    private final IDishCategoryPersistencePort categoryPersistencePort;

    public DishCategoryUseCase(IDishCategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public void saveDishCategory(DishCategoryModel categoryModel) {
        categoryPersistencePort.saveDishCategory(categoryModel);
    }

    @Override
    public List<DishCategoryModel> getAllDishCategories() {
        return categoryPersistencePort.getAllDishCategories();
    }

    @Override
    public DishCategoryModel getDishCategoryById(Long id) {
        return categoryPersistencePort.getDishCategoryById(id);
    }
}
