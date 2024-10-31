package com.umbrellait.carshop_camunda.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiErrorDto {
    private String message;
}
