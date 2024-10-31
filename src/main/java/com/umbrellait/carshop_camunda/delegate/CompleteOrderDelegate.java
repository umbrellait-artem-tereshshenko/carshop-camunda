package com.umbrellait.carshop_camunda.delegate;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.model.AdditionalCarParts;
import com.umbrellait.carshop_camunda.service.CarOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * Camunda Java Delegate class that handles logic for performing
 * business logic of completing car order .
 *
 * @author artem.tereshchenko
 */

@Component
@AllArgsConstructor
@Slf4j
public class CompleteOrderDelegate extends AbstractDelegate {

    private final CarOrderService carOrderService;

    @Override
    public void run(DelegateExecution delegateExecution) {

        CarOrderDto order = (CarOrderDto) delegateExecution.getVariable("order");

        carOrderService.completeOrder(order.getId());

        log.info("Completing car order id: {} . Total price: {}", order.getId(), calculateTotalPrice(order));

    }

    private long calculateTotalPrice(CarOrderDto carOrderDto) {

        long totalPrice = carOrderDto.getCar().price();

        for (AdditionalCarParts additionalCarPartsParts : carOrderDto.getAdditionalCarParts()) {
            totalPrice += additionalCarPartsParts.getPrice();
        }

        return totalPrice;
    }
}
