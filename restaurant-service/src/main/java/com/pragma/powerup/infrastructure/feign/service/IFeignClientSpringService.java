package com.pragma.powerup.infrastructure.feign.service;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.feign.dto.request.EmployeeRequestDto;
import com.pragma.powerup.infrastructure.feign.dto.request.UserRequestDto;
public interface IFeignClientSpringService {
    void createOwner(UserRequestDto userRequestDto);
    void createClient(UserRequestDto userRequestDto);
    Long createEmployee(EmployeeRequestDto employeeRequestDto);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto getUserById(Long id);
}
