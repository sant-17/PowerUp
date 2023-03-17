package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {
}
