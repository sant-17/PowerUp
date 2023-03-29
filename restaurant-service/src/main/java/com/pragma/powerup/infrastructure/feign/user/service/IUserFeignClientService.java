package com.pragma.powerup.infrastructure.feign.user.service;

import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.feign.user.dto.request.EmployeeRequestDto;
import com.pragma.powerup.infrastructure.feign.user.dto.request.UserRequestDto;

public interface IUserFeignClientService {
    void createOwner(UserRequestDto userRequestDto);
    void createClient(UserRequestDto userRequestDto);
    Long createEmployee(EmployeeRequestDto employeeRequestDto);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto getUserById(Long id);
}
