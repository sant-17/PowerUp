package com.pragma.powerup.infrastructure.client;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "localhost:8081/api/user")
public interface IUserClientFeign {

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long id);
}
