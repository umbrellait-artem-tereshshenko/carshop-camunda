package com.umbrellait.carshop_camunda.repository;

import com.umbrellait.carshop_camunda.model.Car;
import com.umbrellait.carshop_camunda.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Car JPA repository
 *
 * @author artem.tereshchenko
 *
 */
public interface CarRepository extends JpaRepository<Car, CarBrand> {
}