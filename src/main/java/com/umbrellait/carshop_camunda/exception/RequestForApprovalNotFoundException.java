package com.umbrellait.carshop_camunda.exception;

/**
 * Request for approval not found exception
 *
 * @author artem.tereshchenko
 *
 */
public class RequestForApprovalNotFoundException extends RuntimeException {

    public RequestForApprovalNotFoundException(String message) {
        super(message);
    }
}
