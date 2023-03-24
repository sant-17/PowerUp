package com.pragma.powerup.application.service;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserPswResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

import java.util.List;
public interface IUserSpringService {
    void saveUserAsOwner(UserRequestDto userRequestDto);
    void saveUserAsEmployee(UserRequestDto userRequestDto);
    void saveUserAsClient(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserPswResponseDto getUserByEmail(String email);

}
