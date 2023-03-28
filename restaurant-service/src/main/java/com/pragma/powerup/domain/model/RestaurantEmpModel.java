package com.pragma.powerup.domain.model;

public class RestaurantEmpModel {
    private Long user;
    private Long restaurant;

    public RestaurantEmpModel(Long user, Long restaurant) {
        this.user = user;
        this.restaurant = restaurant;
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
