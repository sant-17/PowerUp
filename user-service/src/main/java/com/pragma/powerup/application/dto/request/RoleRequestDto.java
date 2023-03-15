package com.pragma.powerup.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class RoleRequestDto {

    @NotBlank(message = "Field 'name' it's required")
    private String name;

    @NotBlank(message = "Field 'description' it's required")
    private String description;
}
