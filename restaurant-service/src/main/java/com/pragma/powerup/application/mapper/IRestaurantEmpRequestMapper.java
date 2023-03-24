package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantEmpRequestDto;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmpRequestMapper {
    RestaurantEmpModel toRestaurantEmp(RestaurantEmpRequestDto restaurantEmpRequestDto);
}
