package com.pragma.powerup.infrastructure.component;

import com.pragma.powerup.application.dto.request.RoleRequestDto;
import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.service.IRoleSpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSaver {
    private final IRoleSpringService roleSpringService;

    @PostConstruct
    public void init(){
        List<RoleResponseDto> roleResponseDtoList = roleSpringService.getAllRoles();
        if (roleResponseDtoList.isEmpty()){
            RoleRequestDto administradorRequestDto = new RoleRequestDto("ADMINISTRADOR", "Un administrador");
            RoleRequestDto propietarioRequestDto = new RoleRequestDto("PROPIETARIO", "Un propietario");
            RoleRequestDto empleadoRequestDto = new RoleRequestDto("EMPLEADO", "Un empleado");
            RoleRequestDto clienteRequestDto = new RoleRequestDto("CLIENTE", "Un cliente");

            roleSpringService.saveRole(administradorRequestDto);
            roleSpringService.saveRole(propietarioRequestDto);
            roleSpringService.saveRole(empleadoRequestDto);
            roleSpringService.saveRole(clienteRequestDto);
        }
    }

}
