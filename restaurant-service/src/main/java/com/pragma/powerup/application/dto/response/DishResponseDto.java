package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {
    private String name;
    private String description;
    private Integer price;
    private String imageUrl;
    private DishCategoryResponseDto category;
}
