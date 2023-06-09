package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.NoUserFoundException;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        Boolean existsEmail = userRepository.selectExistsEmail(userModel.getEmail());
        if (existsEmail){
            throw new UserAlreadyExistsException(userModel.getEmail());
        }
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(userModel));
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userEntityMapper.toUserModel(userRepository.findById(id)
                        .orElse(new UserEntity()));
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userEntityMapper.toUserModel(userRepository.findByEmail(email)
                .orElse(new UserEntity()));
    }
}