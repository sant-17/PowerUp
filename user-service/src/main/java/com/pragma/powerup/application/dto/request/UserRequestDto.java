package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

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

    @NotNull(message = "Field 'identificationNumber' it's required")
    private Long identificationNumber;

    @Pattern(regexp = "^\\+573[0-2]\\d{8}$", message = "Field 'numberPhone' must be a valid number phone. Enter the format +573...")
    @NotBlank(message = "Field 'phoneNumber' it's required")
    private String phoneNumber;

    @NotBlank(message = "Field 'email' it's required")
    @Email(message = "Field 'email' must be a valid email direction. Enter the format name@example.com")
    private String email;

    @NotBlank(message = "Field 'password' it's required")
    private String password;

    @NotNull(message = "Field 'role' it's required (as number)")
    private Long role;
}
