package com.umbrellait.carshop_camunda.facade;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.controller.dto.RequestCarOrderDto;
import com.umbrellait.carshop_camunda.service.CarOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

/**
 * Facade class orchestrating the process of initiation of a car ordering and
 * sending init signal to Camunda engine to start the process.
 *
 * @author artem.tereshchenko
 *
 */

@Service
@AllArgsConstructor
public class CamundaCarProcessInitFacade {

    private final CarOrderService carOrderService;

    @Transactional
    public ProcessInstance initCarOrderProcess(String buyerID, RequestCarOrderDto carOrderDtoDto) {

        CarOrderDto carOrderDto = carOrderService.create(buyerID, carOrderDtoDto);


        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        return runtimeService.createProcessInstanceByKey("car_order_flow")
                .businessKey(carOrderDto.getId().toString())
                .setVariable("order", carOrderDto)
                .execute();
    }
}
