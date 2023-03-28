package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishesRequestDto {
    private Long dish;
    private Integer quantity;
}
