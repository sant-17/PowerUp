package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmpEntityMapper {

    RestaurantEmpEntity toEntity(RestaurantEmpModel restaurantEmpModel);
    RestaurantEmpModel toRestaurantEmpModel(RestaurantEmpEntity restaurantEmpEntity);
}
