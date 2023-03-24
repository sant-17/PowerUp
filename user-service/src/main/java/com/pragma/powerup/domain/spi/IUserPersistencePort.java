package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);
    List<UserModel> getAllUsers();
    UserModel getUserById(Long id);
    UserModel getUserByEmail(String email);
}
