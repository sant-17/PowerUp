package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.DishCategoryResponseDto;
import com.pragma.powerup.domain.model.DishCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishCategoryResponseMapper {
    DishCategoryResponseDto toResponse(DishCategoryModel categoryModel);
    List<DishCategoryResponseDto> toResponseList(List<DishCategoryModel> categoryModelList);

}
