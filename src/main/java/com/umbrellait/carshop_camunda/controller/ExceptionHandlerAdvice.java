package com.umbrellait.carshop_camunda.controller;

import com.umbrellait.carshop_camunda.controller.dto.ApiErrorDto;
import com.umbrellait.carshop_camunda.exception.CarNotFoundException;
import com.umbrellait.carshop_camunda.exception.PersonNotFoundException;
import com.umbrellait.carshop_camunda.exception.RequestForApprovalNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {


    @ExceptionHandler({PersonNotFoundException.class, CarNotFoundException.class, RequestForApprovalNotFoundException.class})
    public ResponseEntity<Object> handleBusinessException(Exception ex) {

        return new ResponseEntity<>(ApiErrorDto.builder()
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGlobalException(Exception ex) {

        return new ResponseEntity<>(ApiErrorDto.builder()
                .message(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
