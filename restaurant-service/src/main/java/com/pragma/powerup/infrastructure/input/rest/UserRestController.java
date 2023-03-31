package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.infrastructure.feign.user.dto.request.UserRequestDto;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserFeignClientService feignClientSpringService;


    @Operation(summary = "Create PROPIETARIO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created with role PROPIETARIO",
                    content = @Content),

            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content),

            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists with that exact email",
                    content = @Content)
    })
    @PostMapping("/create-owner")
    public ResponseEntity<Void> saveUserAsOwner(@Valid @RequestBody UserRequestDto userRequestDto){
        feignClientSpringService.createOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Create CLIENTE")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created with role CLIENTE",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requested params wrong or missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists with that exact email",
                    content = @Content
            )
    })
    @PostMapping("/create-client")
    public ResponseEntity<Void> saveUserAsClient(@Valid @RequestBody UserRequestDto userRequestDto){
        feignClientSpringService.createClient(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
