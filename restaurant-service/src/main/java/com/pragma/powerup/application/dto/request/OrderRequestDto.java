package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @Transient
    private Long client;

    @Transient
    private Date date;

    @Transient
    private String status;

    @NotNull(message = "Field 'restaurant' it's required")
    private Long restaurant;

    private List<OrderDishesRequestDto> dishes;
}
