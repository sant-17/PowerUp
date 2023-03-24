package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class DishUpdateRequestDto {

    private String description;

    @Min(value = 1, message = "Minimum value allowed for the 'price' field is 1")
    private Integer price;

    @Pattern(regexp = "^(true|false)$", message = "Field 'active' only acepts 'true' o 'false' values")
    private String active;
}
