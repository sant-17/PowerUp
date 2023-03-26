package com.pragma.powerup.infrastructure.auth;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.feign.service.IFeignClientSpringService;
import com.pragma.powerup.infrastructure.security.JwtService;
import com.pragma.powerup.infrastructure.security.aut.DetailsUser;
import com.pragma.powerup.infrastructure.security.aut.IDetailsUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IDetailsUserMapper userDetailsMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IFeignClientSpringService feignClientSpringService;


    public AuthenticationResponse  authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = optionalDetailsUser(request.getEmail()).get();
        var jwtToken = jwtService.generateToken(user, user.getRole());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private Optional<DetailsUser> optionalDetailsUser(String username) {
        UserResponseDto userResponseDto = feignClientSpringService.getUserByEmail(username);
        DetailsUser user = userDetailsMapper.toUser(userResponseDto);
        user.setRole(userResponseDto.getRole().getName());
        return Optional.of(user);
    }

    public UserAuthDto getUserAuth(String email) {
        UserResponseDto userResponseDto = feignClientSpringService.getUserByEmail(email);
        DetailsUser user = userDetailsMapper.toUser(userResponseDto);
        user.setRole(userResponseDto.getRole().getName());
        return userDetailsMapper.toUserAuth(user);
    }
}