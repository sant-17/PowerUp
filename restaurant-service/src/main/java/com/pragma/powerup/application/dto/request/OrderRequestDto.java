package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
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

    private Long restaurant;

    private List<OrderDishesRequestDto> dishes;
}
