package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.infrastructure.feign.user.dto.request.UserRequestDto;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
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


    @PostMapping("/create-owner")
    public ResponseEntity<Void> saveUserAsOwner(@Valid @RequestBody UserRequestDto userRequestDto){
        feignClientSpringService.createOwner(userRequestDto);
        userLoginApplication();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create-client")
    public ResponseEntity<Void> saveUserAsClient(@Valid @RequestBody UserRequestDto userRequestDto){
        feignClientSpringService.createClient(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public static void userLoginApplication(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails){
            userDetails = (UserDetails) principal;
        }
        System.out.println(userDetails.getUsername());
    }
}
