package com.umbrellait.carshop_camunda.controller;

import com.umbrellait.carshop_camunda.controller.dto.RequestForApprovalDto;
import com.umbrellait.carshop_camunda.controller.dto.SubmitApprovalRequestDto;
import com.umbrellait.carshop_camunda.facade.CamundaSendApproveDriverLicenseFacade;
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
 * REST API controller for sales manager verification process flow
 *
 * @author artem.tereshchenko
 *
 */

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
@Tag(name = "Sales Manager API ", description = "Sales Manager API")
public class SalesManagerController {

    private final RequestForApprovalService requestForApprovalService;

    private final CamundaSendApproveDriverLicenseFacade camundaSendApproveDriverLicenseFacade;

    @GetMapping
    @Operation(summary = "Allows to get list of request for approval for authorized sales manager")
    public ResponseEntity<List<RequestForApprovalDto>> getList(Principal principal) {
        return ResponseEntity.ok(requestForApprovalService
                .findAllByPerson_Name(principal.getName()));
    }

    @PostMapping
    @Operation(summary = "Allows to accept request for approval for authorized sales manager")
    public ResponseEntity<String> accept(@RequestBody SubmitApprovalRequestDto submitApprovalRequestDto)  {

        camundaSendApproveDriverLicenseFacade.approveDriverLicense(UUID.fromString(submitApprovalRequestDto.id()),
                submitApprovalRequestDto.isApproved());

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("Request for approval driver license with id:  %s completed", submitApprovalRequestDto.id()));
    }

}
