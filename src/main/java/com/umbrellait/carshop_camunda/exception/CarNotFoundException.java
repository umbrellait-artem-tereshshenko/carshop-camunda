package com.umbrellait.carshop_camunda.exception;

/**
 * Car not found exception
 *
 * @author artem.tereshchenko
 *
 */
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String message) {
        super(message);
    }
}
