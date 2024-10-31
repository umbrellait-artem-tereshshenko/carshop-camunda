package com.umbrellait.carshop_camunda.controller;

import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.controller.dto.RequestCarOrderDto;
import com.umbrellait.carshop_camunda.facade.CamundaCarProcessInitFacade;
import com.umbrellait.carshop_camunda.service.CarOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST API controller for car ordering process flow by customer
 *
 * @author artem.tereshchenko
 *
 */


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customer car order controller", description = "Car Order Controller by customer")
public class CustomerCarOrderController {

    private final CamundaCarProcessInitFacade camundaCarProcessFacadeFacade;
    private final CarOrderService carOrderService;

    @PostMapping
    @Operation(summary = "Init car order process flow by customer")
    public ResponseEntity<String> create(Principal principal, @RequestBody RequestCarOrderDto dto) {


        ProcessInstance processInstance = camundaCarProcessFacadeFacade.initCarOrderProcess(principal.getName(),
                dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("Car order created with id:  %s ", processInstance.getBusinessKey()));
    }

    @GetMapping
    @Operation(summary = "Get list of car orders by customer")
    public ResponseEntity<List<CarOrderDto>> list(Principal principal) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(carOrderService.findCardOrdersPersonID(principal.getName()));
    }
}
