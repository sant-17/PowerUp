package com.pragma.powerup.infrastructure.feign;

import com.pragma.powerup.infrastructure.feign.dto.request.EmployeeRequestDto;
import com.pragma.powerup.infrastructure.feign.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "localhost:8085/api/v1/user")
public interface IUserClientFeign {

    @PostMapping("/save-owner")
    public void saveUserAsOwner(@RequestBody UserRequestDto userRequestDto);

    @PostMapping("/save-employee")
    public void saveUserAsEmployee(@RequestBody EmployeeRequestDto employeeRequestDto);

    @PostMapping("/save-client")
    public void saveUserAsClient(@RequestBody UserRequestDto userRequestDto);

    @GetMapping("/id/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/email/{email}")
    public UserResponseDto getUserByEmail(@PathVariable("email") String email);


}
