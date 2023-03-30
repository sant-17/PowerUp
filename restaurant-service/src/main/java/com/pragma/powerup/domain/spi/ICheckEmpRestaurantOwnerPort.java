package com.pragma.powerup.domain.spi;

public interface ICheckEmpRestaurantOwnerPort {
    void checkOwner(Long id, String username);
}
