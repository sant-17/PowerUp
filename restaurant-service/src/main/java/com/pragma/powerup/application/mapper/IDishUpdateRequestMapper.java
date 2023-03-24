package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishUpdateRequestMapper {
    DishModel toDish(DishUpdateRequestDto dishUpdateRequestDto);
}
