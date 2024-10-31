package com.umbrellait.carshop_camunda.controller;

import com.umbrellait.carshop_camunda.controller.dto.RequestForApprovalDto;
import com.umbrellait.carshop_camunda.controller.dto.SubmitApprovalRequestDto;
import com.umbrellait.carshop_camunda.facade.CamundaSendApproveLoanFacade;
import com.umbrellait.carshop_camunda.service.RequestForApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * REST API controller for bank manager verification process flow
 *
 * @author artem.tereshchenko
 *
 */

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
@Tag(name = "Bank Manager API ", description = "Bank Manager API")
public class BankManagerController {

    private final RequestForApprovalService requestForApprovalService;

    private final CamundaSendApproveLoanFacade camundaSendApproveLoanFacade;

    @GetMapping
    @Operation(summary = "Allows to get list of request for approval for authorized bank manager")
    public ResponseEntity<List<RequestForApprovalDto>> getList(Principal principal) {
        return ResponseEntity.ok(requestForApprovalService
                .findAllByPerson_Name(principal.getName()));
    }

    @PostMapping
    @Operation(summary = "Allows to accept request for approval for authorized bank manager")
    public ResponseEntity<String> accept(@RequestBody SubmitApprovalRequestDto submitApprovalRequestDto)  {

        camundaSendApproveLoanFacade.approveLoan(UUID.fromString(submitApprovalRequestDto.id()),
                submitApprovalRequestDto.isApproved());

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("Request for approval loan with id:  %s completed", submitApprovalRequestDto.id()));
    }

}
