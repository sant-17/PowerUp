package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {
    void saveUserAsOwner(UserModel userModel);
    void saveUserAsEmployee(UserModel userModel);
    void saverUserAsClient(UserModel userModel);
    List<UserModel> getAllUsers();
    UserModel getUserById(Long id);
    UserModel getUserByEmail(String email);
}
