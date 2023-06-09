package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserFeignResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.service.IUserSpringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserSpringService userSpringService;

    @Operation(summary = "Add a new user with role PROPIETARIO")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Field missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict. User already exists with requested email",
                    content = @Content
            )
    })
    @PostMapping("/save-owner")
    public ResponseEntity<Void> saveUserAsOwner(@Valid @RequestBody UserRequestDto userRequestDto){
        userSpringService.saveUserAsOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new user with role EMPLOYEE")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Field missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict. User already exists with requested email",
                    content = @Content
            )
    })
    @PostMapping("/save-employee")
    public ResponseEntity<Void> saveUserAsEmployee(@Valid @RequestBody UserRequestDto userRequestDto){
        userSpringService.saveUserAsEmployee(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new user with role CLIENT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Field missing",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict. User already exists with requested email",
                    content = @Content
            )
    })
    @PostMapping("/save-client")
    public ResponseEntity<Void> saveUserAsClient(@Valid @RequestBody UserRequestDto userRequestDto){
        userSpringService.saveUserAsClient(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User founded by Id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserResponseDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Field missing",
                    content = @Content
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userSpringService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserFeignResponseDto> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userSpringService.getUserByEmail(email));
    }
}