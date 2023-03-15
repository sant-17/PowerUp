package com.pragma.powerup.application.service.impl;

import com.pragma.powerup.application.dto.request.RoleRequestDto;
import com.pragma.powerup.application.mapper.IRoleRequestMapper;
import com.pragma.powerup.application.mapper.IRoleResponseMapper;
import com.pragma.powerup.application.service.IRoleSpringService;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RoleSpringService implements IRoleSpringService {
    private final IRoleServicePort roleServicePort;
    private final IRoleRequestMapper roleRequestMapper;
    private final IRoleResponseMapper roleResponseMapper;
    @Override
    public void saveRole(RoleRequestDto roleRequestDto) {
        RoleModel roleModel = roleRequestMapper.toRole(roleRequestDto);
        roleServicePort.saveRole(roleModel);
    }
}
