package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        if (!userModel.getPhoneNumber().substring(0,1).equals("+")){
            userModel.setPhoneNumber("+".concat(userModel.getPhoneNumber()));
        }
        userPersistencePort.saveUser(userModel);
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
