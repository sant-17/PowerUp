package com.pragma.powerup.infrastructure.feign.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleResponseDto {
    private String name;
    private String description;
}
