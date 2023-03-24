package com.pragma.powerup.application.service.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserFeignResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.mapper.IUserPswResponseMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.application.service.IUserSpringService;
import com.pragma.powerup.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserSpringService implements IUserSpringService {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IUserPswResponseMapper userPswResponseMapper;

    @Override
    public void saveUserAsOwner(UserRequestDto userRequestDto) {
        userRequestDto.setRole(2L);
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void saveUserAsEmployee(UserRequestDto userRequestDto) {
        userRequestDto.setRole(3L);
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void saveUserAsClient(UserRequestDto userRequestDto) {
        userRequestDto.setRole(4L);
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userResponseMapper.toResponse(userServicePort.getUserById(id));
    }

    @Override
    public UserFeignResponseDto getUserByEmail(String email) {
        return userPswResponseMapper.toResponse(userServicePort.getUserByEmail(email));
    }
}
