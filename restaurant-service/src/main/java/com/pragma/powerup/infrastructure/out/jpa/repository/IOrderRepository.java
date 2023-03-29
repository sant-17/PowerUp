package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(o) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM OrderEntity o " +
            "WHERE o.client = ?1 " +
            "AND (o.status = ?2 " +
            "OR o.status = ?3 " +
            "OR o.status = ?4) "

    )
    Boolean orderByClientInProcess(Long client, String firstStatus, String secondStatus, String thirdStatus);

    List<OrderEntity> findByRestaurantAndStatus(RestaurantEntity restaurant, String status, Pageable pageable);

}
