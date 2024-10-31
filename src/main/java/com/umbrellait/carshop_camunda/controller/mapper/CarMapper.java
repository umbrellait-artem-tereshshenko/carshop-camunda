package com.umbrellait.carshop_camunda.controller.mapper;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.model.Car;
import org.mapstruct.*;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    Car toEntity(CarOrderDto.CarDto carDto);

    CarOrderDto.CarDto toDto(Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarOrderDto.CarDto carDto, @MappingTarget Car car);


}