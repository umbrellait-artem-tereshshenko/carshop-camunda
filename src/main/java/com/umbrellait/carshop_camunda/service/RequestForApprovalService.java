package com.umbrellait.carshop_camunda.service;

import com.umbrellait.carshop_camunda.controller.dto.RequestForApprovalDto;
import com.umbrellait.carshop_camunda.controller.mapper.RequestForApprovalMapper;
import com.umbrellait.carshop_camunda.exception.RequestForApprovalNotFoundException;
import com.umbrellait.carshop_camunda.model.*;
import com.umbrellait.carshop_camunda.repository.RequestForApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Request for approval service class incapsulates business logic for order
 * approval by managers and banks
 *
 * @author artem.tereshchenko
 *
 */

@RequiredArgsConstructor
@Service
public class RequestForApprovalService {

    private final RequestForApprovalRepository requestForApprovalRepository;
    private final RequestForApprovalMapper requestForApprovalMapperMapper;

    @Transactional
    public RequestForApprovalDto createRequestForApproval(CarOrder carOrder, Person person) {

        RequestForApproval requestForApproval = RequestForApproval.builder()
                .carOrder(carOrder)
                .person(person)
                .build();

        return requestForApprovalMapperMapper.toDto(
                requestForApprovalRepository.save(requestForApproval));
    }

    @Transactional(readOnly = true)
    public List<RequestForApprovalDto> findAllByPerson_Name(String managerName) {
        return requestForApprovalRepository.findAllByPerson_Name(managerName)
                .stream()
                .map(requestForApprovalMapperMapper::toDto)
                .toList();
    }

    @Transactional
    public RequestForApprovalDto acceptRequestForApproval(UUID uuid, boolean isApproved) {

        RequestForApproval requestForApproval = requestForApprovalRepository.findById(uuid)
                .orElseThrow(() -> new RequestForApprovalNotFoundException(String.format("Request for approval with id %s is not found", uuid)));
        requestForApproval.setApproved(isApproved);
        requestForApproval = requestForApprovalRepository.save(requestForApproval);

        return requestForApprovalMapperMapper.toDto(requestForApproval);

    }
}
