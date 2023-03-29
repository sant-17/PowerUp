package com.pragma.powerup.infrastructure.security.aut;

import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.infrastructure.auth.UserAuthDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDetailsUserMapper {

    @Mapping(source = "userResponseDto.role.name", target = "role")
    DetailsUser toUser(UserResponseDto userResponseDto);
    UserAuthDto toUserAuth(DetailsUser user);
}
