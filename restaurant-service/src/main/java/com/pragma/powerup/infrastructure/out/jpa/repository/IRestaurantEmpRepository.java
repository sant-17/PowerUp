package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantEmpRepository extends JpaRepository<RestaurantEmpEntity, Long> {
}
