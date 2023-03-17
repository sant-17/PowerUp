package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.DishCategoryRequestDto;
import com.pragma.powerup.domain.model.DishCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishCategoryRequestMapper {

    DishCategoryModel toDishCategory(DishCategoryRequestDto categoryRequestDto);
}
