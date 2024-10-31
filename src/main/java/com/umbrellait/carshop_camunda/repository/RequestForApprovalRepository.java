package com.umbrellait.carshop_camunda.repository;

import com.umbrellait.carshop_camunda.model.RequestForApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * RequestForApproval JPA repository
 *
 * @author artem.tereshchenko
 *
 */
public interface RequestForApprovalRepository extends JpaRepository<RequestForApproval, UUID> {

    List<RequestForApproval> findAllByPerson_Name(String managerName);

    @Override
    Optional<RequestForApproval> findById(UUID id);
}