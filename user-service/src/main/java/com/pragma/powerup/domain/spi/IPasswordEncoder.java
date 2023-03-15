package com.pragma.powerup.domain.spi;

public interface IPasswordEncoder {

    String encode(String password);
}
