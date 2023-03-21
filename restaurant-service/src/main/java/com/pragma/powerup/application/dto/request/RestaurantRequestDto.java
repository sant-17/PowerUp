package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantRequestDto {

    @NotBlank(message = "Field 'name' it's required")
    @Pattern(regexp = "(?!^\\d+$)^.+$", message = "Field 'name' must not be only numbers")
    private String name;

    @NotBlank(message = "Field 'address' it's required")
    private String address;

    @NotNull(message = "Field 'owner' it's required")
    private Long owner;

    @Pattern(regexp = "^\\+573[0-2]\\d{8}$", message = "Field 'numberPhone' must be a valid number phone. Enter the format +573...")
    @NotBlank(message = "Field 'phoneNumber' it's required")
    @Size(max = 13, message = "Field 'phoneNumber' must be maximum 13 characters")
    private String phoneNumber;

    @NotNull(message = "Field 'nit' it's required")
    private Long nit;

    @NotBlank(message = "Field 'logoUrl' it's required")
    private String logoUrl;
}
