package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;

    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        RoleEntity roleEntity = roleRepository.save(roleEntityMapper.toEntity(roleModel));
        return roleEntityMapper.toRoleModel(roleEntity);
    }
}
