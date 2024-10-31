package com.umbrellait.carshop_camunda.facade;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.controller.dto.RequestForApprovalDto;
import com.umbrellait.carshop_camunda.service.CarOrderService;
import com.umbrellait.carshop_camunda.service.RequestForApprovalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Facade class orchestrating the process of loan approval submit from REST endpoint
 * by authorized bank manager and sending it to Camunda bypassing state of UserTask awaiting.
 *
 * @author artem.tereshchenko
 *
 */

@Service
@AllArgsConstructor
public class CamundaSendApproveLoanFacade {

    private final TaskService taskService;
    private final RequestForApprovalService requestForApprovalService;
    private final CarOrderService carOrderService;



    @Transactional
    public void approveLoan(UUID requestForApprovalID, boolean isApproved) {

        RequestForApprovalDto requestForApprovalDto = requestForApprovalService
                .acceptRequestForApproval(requestForApprovalID, isApproved);

        CarOrderDto carOrderDto = carOrderService
                .approveLoan(requestForApprovalDto.carOrder().getId(), isApproved);

        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(requestForApprovalDto.carOrder().getId().toString()).list().getFirst();

        Map<String, Object> inputData = new HashMap<>();
        inputData.put("order", carOrderDto);

        taskService.complete(task.getId(), inputData);

    }
}
