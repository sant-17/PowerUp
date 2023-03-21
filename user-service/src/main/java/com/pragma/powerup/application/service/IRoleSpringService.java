package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.RoleRequestDto;
import com.pragma.powerup.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleSpringService {
    void saveRole(RoleRequestDto roleRequestDto);
    List<RoleResponseDto> getAllUsers();
}
