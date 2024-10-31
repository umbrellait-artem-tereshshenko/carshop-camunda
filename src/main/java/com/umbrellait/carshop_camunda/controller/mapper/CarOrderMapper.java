package com.umbrellait.carshop_camunda.controller.mapper;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.model.CarOrder;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {PersonMapper.class, CarMapper.class})
public interface CarOrderMapper {
    CarOrder toEntity(CarOrderDto carOrderDto);

    CarOrderDto toDto(CarOrder carOrder);

}