package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.client.IUserClientFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserClientFeign userClientFeign;

    @PostMapping("/create-owner")
    public ResponseEntity<Void> saveUserAsOwner(@Valid @RequestBody UserRequestDto userRequestDto){
        try{
            UserResponseDto userResponseDto = userClientFeign.getUserByEmail(userRequestDto.getEmail());
        }
        catch (Exception e){
            userClientFeign.saveUserAsOwner(userRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create-client")
    public ResponseEntity<Void> saveUserAsClient(@Valid @RequestBody UserRequestDto userRequestDto){
        try{
            UserResponseDto userResponseDto = userClientFeign.getUserByEmail(userRequestDto.getEmail());
        }
        catch (Exception e){
            userClientFeign.saveUserAsOwner(userRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
