package com.pragma.powerup.infrastructure.feign.user.service.imp;

import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.feign.user.IUserClientFeign;
import com.pragma.powerup.infrastructure.feign.user.dto.request.EmployeeRequestDto;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import com.pragma.powerup.infrastructure.feign.user.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserFeignClientService implements IUserFeignClientService {

    private final IUserClientFeign userClientFeign;

    @Override
    public void createOwner(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        UserResponseDto userResponseDto = userClientFeign.getUserByEmail(email);

        if (userResponseDto.getEmail() != null){
            throw new UserAlreadyExistsException(email);
        }

        userClientFeign.saveUserAsOwner(userRequestDto);
    }

    @Override
    public void createClient(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        UserResponseDto userResponseDto = userClientFeign.getUserByEmail(email);

        if (userResponseDto.getEmail() != null){
            throw new UserAlreadyExistsException(email);
        }

        userClientFeign.saveUserAsClient(userRequestDto);
    }

    @Override
    public Long createEmployee(EmployeeRequestDto employeeRequestDto) {
        String email = employeeRequestDto.getEmail();
        UserResponseDto userResponseDto = userClientFeign.getUserByEmail(email);

        if (userResponseDto.getEmail() != null){
            throw new UserAlreadyExistsException(email);
        }

        userClientFeign.saveUserAsEmployee(employeeRequestDto);
        return userClientFeign.getUserByEmail(email).getId();
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userClientFeign.getUserByEmail(email);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userClientFeign.getUserById(id);
    }

    public static String usernameToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails){
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }
}
