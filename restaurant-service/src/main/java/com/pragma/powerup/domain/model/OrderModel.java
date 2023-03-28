package com.pragma.powerup.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderModel {
    private Long id;
    private Long client;
    private LocalDateTime date;
    private String status;
    private RestaurantEmpModel chef;
    private RestaurantModel restaurant;
    private List<OrderDishesModel> dishes;

    public OrderModel(Long id, Long client, LocalDateTime date, String status, RestaurantEmpModel chef, RestaurantModel restaurant, List<OrderDishesModel> dishes) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.status = status;
        this.chef = chef;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestaurantEmpModel getChef() {
        return chef;
    }

    public void setChef(RestaurantEmpModel chef) {
        this.chef = chef;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderDishesModel> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDishesModel> dishes) {
        this.dishes = dishes;
    }
}
