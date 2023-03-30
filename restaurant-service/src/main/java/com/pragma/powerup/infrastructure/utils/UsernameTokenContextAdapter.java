package com.pragma.powerup.infrastructure.utils;

import com.pragma.powerup.domain.spi.IUserContextPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class UsernameTokenContextAdapter implements IUserContextPort {

    @Override
    public String getUserContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails){
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }


}
