package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPswResponseDto {
    private Long id;
    private String name;
    private String surname;
    private Long identificationNumber;
    private String phoneNumber;
    private String password;
    private String email;
    private RoleResponseDto role;
}
