package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class DishRequestDto {

    @NotBlank(message = "Field 'name' it's required")
    private String name;

    @NotBlank(message = "Field 'description' it's required")
    private String description;

    @NotNull(message = "Field 'price' it's required")
    @Min(value = 1, message = "Minimum value allowed for the 'price' field is 1")
    private Integer price;

    @NotBlank(message = "Field 'imageUrl' it's required")
    private String imageUrl;

    @NotNull(message = "Field 'category' it's required")
    private Long category;

    @NotNull(message = "Field 'restaurant' it's required")
    private Long restaurant;
}
