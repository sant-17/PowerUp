package com.pragma.powerup.domain.model;

public class OrderDishesModel {
    private Long order;
    private Long dish;
    private Integer quantity;

    public OrderDishesModel(Long order, Long dish, Integer quantity) {
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getDish() {
        return dish;
    }

    public void setDish(Long dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
