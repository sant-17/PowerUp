package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RoleRequestDto;
import com.pragma.powerup.application.service.impl.RoleSpringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/role/")
public class RoleRestController {
    private final RoleSpringService roleSpringService;

    @Operation(summary = "Add a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request. Field missing", content = @Content)

    })
    @PostMapping("/")
    public ResponseEntity<Void> saveRole(@RequestBody RoleRequestDto roleRequestDto){
        roleSpringService.saveRole(roleRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
