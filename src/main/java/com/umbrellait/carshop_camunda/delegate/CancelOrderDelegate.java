package com.umbrellait.carshop_camunda.delegate;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.service.CarOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * Camunda Java Delegate class that handles logic for performing
 * business logic of canceling car order .
 *
 * @author artem.tereshchenko
 *
 */

@Component
@AllArgsConstructor
@Slf4j
public class CancelOrderDelegate extends AbstractDelegate {

    private final CarOrderService carOrderService;

    @Override
    public void run(DelegateExecution delegateExecution) {

        CarOrderDto order = (CarOrderDto)delegateExecution.getVariable("order");

        carOrderService.cancelOrder(order.getId());

        log.info("Cancelling car order id: {} ", order.getId());

    }
}
