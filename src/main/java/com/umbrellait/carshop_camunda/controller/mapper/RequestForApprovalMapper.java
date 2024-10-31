package com.umbrellait.carshop_camunda.controller.mapper;

import com.umbrellait.carshop_camunda.controller.dto.RequestForApprovalDto;
import com.umbrellait.carshop_camunda.model.RequestForApproval;
import org.mapstruct.*;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING, uses = {CarOrderMapper.class, PersonMapper.class, CarMapper.class})
public interface RequestForApprovalMapper {

    RequestForApproval toEntity(RequestForApprovalDto requestForApprovalDto);

    RequestForApprovalDto toDto(RequestForApproval requestForApproval);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RequestForApproval partialUpdate(RequestForApprovalDto requestForApprovalDto, @MappingTarget RequestForApproval requestForApproval);


}