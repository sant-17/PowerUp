package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }


    @Override
    public void saveDish(DishModel dishModel) {
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public void updateDishById(Long id, DishModel dishModel) {
        dishPersistencePort.updateDishById(id, dishModel);
    }

    @Override
    public void setDishActive(Long id, DishModel dishModel) {
        dishPersistencePort.setDishActive(id, dishModel);
    }

    @Override
    public List<DishModel> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize) {
        return dishPersistencePort.getAllDishesPaging(restaurant, pageNumber, pageSize);
    }


}
