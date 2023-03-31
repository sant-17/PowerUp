package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishCategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IDishRepositoryTest {

    @Autowired private IDishRepository underTest;
    @Autowired private IRestaurantRepository restaurantRepository;
    @Autowired private IDishCategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        DishCategoryEntity categoryBebida = DishRepositoryDataTest.getDishes().get(0).getCategory();
        DishCategoryEntity categoryCarne = DishRepositoryDataTest.getDishes().get(1).getCategory();
        RestaurantEntity restaurant = DishRepositoryDataTest.getDishes().get(0).getRestaurant();
        List<DishEntity> dishEntities = DishRepositoryDataTest.getDishes();

        categoryRepository.saveAll(Arrays.asList(categoryBebida, categoryCarne));
        restaurantRepository.save(restaurant);
        underTest.saveAll(dishEntities);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        restaurantRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void findAllByRestaurantId() {
        //given
        int pageNumber = 0;
        int pageSize = 5;

        List<DishEntity> expected = DishRepositoryDataTest.getDishes();

        Long idRestaurant = DishRepositoryDataTest.getDishes().get(0).getRestaurant().getId();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by( "category").descending());

        //when
        List<DishEntity> dishEntities = underTest.findAllByRestaurantId(idRestaurant, pageable);

        //then
        assertThat(dishEntities.size()).isEqualTo(3);
        assertThat(dishEntities.get(0).getCategory().getName())
                .isEqualTo(expected.get(1).getCategory().getName());

    }
}