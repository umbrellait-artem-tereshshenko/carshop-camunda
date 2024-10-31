package com.umbrellait.carshop_camunda.controller.dto;

import com.umbrellait.carshop_camunda.model.AdditionalCarParts;
import com.umbrellait.carshop_camunda.model.CarBrand;

import java.util.Set;

/**
 * DTO for {@link com.umbrellait.carshop_camunda.model.CarOrder}
 */
public record RequestCarOrderDto(CarBrand carCarBrand, Set<AdditionalCarParts> additionalCarParts, boolean loan,
                                  String managerName, String driverLicense) {
}