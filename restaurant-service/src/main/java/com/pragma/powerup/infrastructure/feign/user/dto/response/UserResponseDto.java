package com.pragma.powerup.infrastructure.feign.user.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private Long identificationNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private RoleResponseDto role;
}
