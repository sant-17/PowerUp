package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.ICheckDishRestaurantOwnerPort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IUserContextPort;

import java.util.List;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IUserContextPort userContext;
    private final ICheckDishRestaurantOwnerPort checkDishRestaurantOwnerPort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IUserContextPort userContext, ICheckDishRestaurantOwnerPort checkDishRestaurantOwnerPort) {
        this.dishPersistencePort = dishPersistencePort;
        this.userContext = userContext;
        this.checkDishRestaurantOwnerPort = checkDishRestaurantOwnerPort;
    }


    @Override
    public void saveDish(DishModel dishModel) {
        checkDishRestaurantOwnerPort.checkUsers(dishModel.getRestaurant().getId(), userContext.getUserContext());
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
        DishModel dishUpdated = dishPersistencePort.getDishById(id);
        checkDishRestaurantOwnerPort.checkUsers(dishUpdated.getRestaurant().getOwner(), userContext.getUserContext());

        if (dishModel.getPrice() != null){
            dishUpdated.setPrice(dishModel.getPrice());
        }
        if (dishModel.getDescription() != null){
            dishUpdated.setDescription(dishModel.getDescription());
        }

        dishPersistencePort.updateDishById(id, dishUpdated);
    }

    @Override
    public void setDishActive(Long id, DishModel dishModel) {
        DishModel dishUpdated = dishPersistencePort.getDishById(id);
        checkDishRestaurantOwnerPort.checkUsers(dishUpdated.getRestaurant().getOwner(), userContext.getUserContext());

        if (dishModel.getActive() != null){
            dishUpdated.setActive(dishModel.getActive());
        }
        dishPersistencePort.setDishActive(id, dishUpdated);
    }

    @Override
    public List<DishModel> getAllDishesPaging(Long restaurant, Integer pageNumber, Integer pageSize) {
        return dishPersistencePort.getAllDishesPaging(restaurant, pageNumber, pageSize);
    }


}
