package com.umbrellait.carshop_camunda.delegate;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.model.CarOrder;
import com.umbrellait.carshop_camunda.model.Person;
import com.umbrellait.carshop_camunda.repository.CarOrderRepository;
import com.umbrellait.carshop_camunda.repository.PersonRepository;
import com.umbrellait.carshop_camunda.service.RequestForApprovalService;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * Camunda Java Delegate class that handles logic for performing
 * business logic of sending request for approval driver license to specifier
 * manager.
 *
 * @author artem.tereshchenko
 *
 */

@Component
@AllArgsConstructor
public class SendRequestForApprovalToManagerDelegate extends AbstractDelegate {

    private final RequestForApprovalService requestForApprovalService;

    private final CarOrderRepository carOrderRepository;
    private final PersonRepository personRepository;

    @Override
    public void run(DelegateExecution delegateExecution) {

        CarOrderDto order = (CarOrderDto)delegateExecution.getVariable("order");

        CarOrder carOrder = carOrderRepository.getReferenceById(order.getId());
        Person manager = personRepository.getReferenceById(carOrder.getManager().getUsername());

        requestForApprovalService.createRequestForApproval(carOrder, manager);

    }
}
