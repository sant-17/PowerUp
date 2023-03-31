package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long client;
    private LocalDateTime date;
    private String status;
    private RestaurantEmpResponseDto chef;
    private RestaurantResponseDto restaurant;
}
