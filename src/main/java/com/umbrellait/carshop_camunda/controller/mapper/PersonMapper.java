package com.umbrellait.carshop_camunda.controller.mapper;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.model.Person;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {
    Person toEntity(CarOrderDto.PersonDto personDto);

    CarOrderDto.PersonDto toDto(Person person);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person partialUpdate(CarOrderDto.PersonDto personDto, @MappingTarget Person person);

}