package com.umbrellait.carshop_camunda.repository;

import com.umbrellait.carshop_camunda.model.CarOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Car order JPA repository
 *
 * @author artem.tereshchenko
 *
 */
public interface CarOrderRepository extends JpaRepository<CarOrder, UUID> {
    List<CarOrder> findAllByBuyer_Name(String name);

    @Modifying
    @Query(value = "update car_order set status = :status where id = :id", nativeQuery = true)
    void changeStatus(@Param("id") UUID id, @Param("status")String status);
}