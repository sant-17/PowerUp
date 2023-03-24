package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUserAsOwner(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userPersistencePort.saveUserAsOwner(userModel);
    }

    @Override
    public void saveUserAsEmployee(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userPersistencePort.saveUserAsEmployee(userModel);
    }

    @Override
    public void saverUserAsClient(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userPersistencePort.saveUserAsClient(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public UserModel getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }
}
