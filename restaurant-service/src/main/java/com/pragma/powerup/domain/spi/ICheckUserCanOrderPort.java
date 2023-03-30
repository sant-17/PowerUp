package com.pragma.powerup.domain.spi;

public interface ICheckUserCanOrderPort {
    Boolean canUserMakeNewOrder(String username);
}
