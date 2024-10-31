package com.umbrellait.carshop_camunda.exception;

/**
 * Person not found exception
 *
 * @author artem.tereshchenko
 *
 */
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}
