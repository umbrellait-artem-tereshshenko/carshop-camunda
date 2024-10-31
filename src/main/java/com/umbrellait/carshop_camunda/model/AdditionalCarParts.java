package com.umbrellait.carshop_camunda.model;

import lombok.Getter;

/**
 * Additional car parts enumeration with its price in smallest monetary unit.
 *
 * @author artem.tereshchenko
 *
 */
@Getter
public enum AdditionalCarParts {

    CLIMATE_CONTROL(500),
    CHIP_TUNING(1000),
    TURBOCHARGER(2000);

    private final long price;

    AdditionalCarParts(long price) {
        this.price = price;
    }

}
