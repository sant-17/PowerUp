package com.pragma.powerup.domain.model;

public class RestaurantEmpModel {
    private Long id;
    private Long user;
    private Long restaurant;

    public RestaurantEmpModel(Long id, Long user, Long restaurant) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Long restaurant) {
        this.restaurant = restaurant;
    }
}
