package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderDishesRequestDto {
    private Long dish;

    @Min(value = 1, message = "Minimum value allowed for the 'quantity' field is 1")
    private Integer quantity;

}
