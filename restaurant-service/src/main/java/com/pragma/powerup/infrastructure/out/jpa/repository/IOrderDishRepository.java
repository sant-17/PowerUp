package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishesEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishesEntity, OrderDishesId> {
}
