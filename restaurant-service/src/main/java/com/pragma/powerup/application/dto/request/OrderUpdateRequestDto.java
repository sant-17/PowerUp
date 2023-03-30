package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderUpdateRequestDto {

    @NotBlank(message = "Field 'chef' it's required")
    private Long chef;
}
