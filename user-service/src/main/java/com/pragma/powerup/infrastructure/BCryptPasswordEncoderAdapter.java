package com.pragma.powerup.infrastructure;

import com.pragma.powerup.domain.spi.IPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter implements IPasswordEncoder {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
