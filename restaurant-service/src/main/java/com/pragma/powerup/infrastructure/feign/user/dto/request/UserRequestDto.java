package com.pragma.powerup.infrastructure.feign.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Field 'name' it's required")
    private String name;

    @NotBlank(message = "Field 'surname' it's required")
    private String surname;

    @Pattern(regexp = "^[0-9]*$", message = "Field 'identificationNumber' must be only numbers")
    @NotNull(message = "Field 'identificationNumber' it's required")
    private String identificationNumber;

    @Pattern(regexp = "^\\+?573[0-2]\\d{8}$", message = "Field 'numberPhone' must be a valid number phone. Enter the format +573...")
    @NotBlank(message = "Field 'phoneNumber' it's required")
    private String phoneNumber;

    @NotBlank(message = "Field 'email' it's required")
    @Email(message = "Field 'email' must be a valid email direction. Enter the format name@example.com")
    private String email;

    @NotBlank(message = "Field 'password' it's required")
    private String password;
}
