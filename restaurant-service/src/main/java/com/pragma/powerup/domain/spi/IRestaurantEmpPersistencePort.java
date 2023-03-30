package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantEmpModel;

public interface IRestaurantEmpPersistencePort {
    RestaurantEmpModel saveRestaurantEmp(RestaurantEmpModel restaurantEmpModel);
    RestaurantEmpModel getEmployeeById(Long id);
}
