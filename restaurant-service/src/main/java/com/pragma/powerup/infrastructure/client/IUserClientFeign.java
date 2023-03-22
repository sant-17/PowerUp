package com.pragma.powerup.infrastructure.client;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "localhost:8085/api/v1/user")
public interface IUserClientFeign {

    @GetMapping("/id/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/email/{email}")
    public UserResponseDto getUserByEmail(@PathVariable("email") String email);


}
