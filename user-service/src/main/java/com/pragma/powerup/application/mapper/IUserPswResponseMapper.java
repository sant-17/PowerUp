package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.UserPswResponseDto;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserPswResponseMapper {
    UserPswResponseDto toResponse(UserModel userModel);
}
