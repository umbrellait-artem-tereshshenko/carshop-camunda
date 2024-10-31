package com.umbrellait.carshop_camunda.delegate;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.exception.PersonNotFoundException;
import com.umbrellait.carshop_camunda.model.CarOrder;
import com.umbrellait.carshop_camunda.model.Person;
import com.umbrellait.carshop_camunda.model.Role;
import com.umbrellait.carshop_camunda.repository.CarOrderRepository;
import com.umbrellait.carshop_camunda.service.PersonService;
import com.umbrellait.carshop_camunda.service.RequestForApprovalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SendRequestForApprovalToBankDelegate extends AbstractDelegate {

    private final RequestForApprovalService requestForApprovalService;

    private final CarOrderRepository carOrderRepository;
    private final PersonService personService;

    @Override
    @Transactional
    public void run(DelegateExecution delegateExecution) {

        log.info("Sending request for approval to bank");

        CarOrderDto order = (CarOrderDto)delegateExecution.getVariable("order");

        //Find any manager with bank role
        Optional<Person> person = personService.findAnyPersonWithRole(Role.ROLE_BANK);

        CarOrder carOrder = carOrderRepository.getReferenceById(order.getId());

        requestForApprovalService.createRequestForApproval(carOrder, person.orElseThrow(
                () -> new PersonNotFoundException("Person with bank role not found")));
    }
}
