package com.umbrellait.carshop_camunda.service;

import com.umbrellait.carshop_camunda.exception.CarNotFoundException;
import com.umbrellait.carshop_camunda.model.Car;
import com.umbrellait.carshop_camunda.model.CarBrand;
import com.umbrellait.carshop_camunda.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Car  service class incapsulates business logic for CRUD car operations
 *
 * @author artem.tereshchenko
 *
 */


@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public Car findCarById(CarBrand carBrand) {
        return carRepository.findById(carBrand).orElseThrow(
                () -> new CarNotFoundException(String.format("Car with brand %s is not found", carBrand)));
    }
}
