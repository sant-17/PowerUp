package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequestDto {
    private String name;
    private String description;
    private Integer price;
    private String imageUrl;
    private Long category;
    private Long restaurant;
}
