package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderUpdateRequestDto;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderUpdateRequestMapper {
    OrderModel toOrder(OrderUpdateRequestDto orderUpdateRequestDto);
}
