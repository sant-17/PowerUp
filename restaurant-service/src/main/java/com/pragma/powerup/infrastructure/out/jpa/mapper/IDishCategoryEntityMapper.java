package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.DishCategoryModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishCategoryEntityMapper {
    DishCategoryEntity toEntity(DishCategoryModel categoryModel);
    DishCategoryModel toDishCategoryModel(DishCategoryEntity categoryEntity);
    List<DishCategoryModel> toDishCategoryList(List<DishCategoryEntity> categoryEntityList);
}
