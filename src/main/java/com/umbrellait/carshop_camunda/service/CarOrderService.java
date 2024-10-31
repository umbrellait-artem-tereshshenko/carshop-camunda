package com.umbrellait.carshop_camunda.service;

import com.umbrellait.carshop_camunda.controller.mapper.CarOrderMapper;
import com.umbrellait.carshop_camunda.controller.dto.CarOrderDto;
import com.umbrellait.carshop_camunda.controller.dto.RequestCarOrderDto;
import com.umbrellait.carshop_camunda.model.CarOrder;
import com.umbrellait.carshop_camunda.model.CarOrderStatus;
import com.umbrellait.carshop_camunda.model.Person;
import com.umbrellait.carshop_camunda.repository.CarOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Car ordering service class incapsulates business logic for order management
 *
 * @author artem.tereshchenko
 *
 */

@RequiredArgsConstructor
@Service
public class CarOrderService {

    private final CarOrderRepository carOrderRepository;
    private final PersonService personService;
    private final CarService carService;

    private final CarOrderMapper carOrderMapper;

    @Transactional
    public CarOrderDto create(String buyerID, RequestCarOrderDto dto) {

        var carOrder = CarOrder
                .builder()
                .buyer((Person) personService.loadUserByUsername(buyerID))
                .manager((Person) personService.loadUserByUsername(dto.managerName()))
                .additionalCarParts(dto.additionalCarParts())
                .loan(dto.loan())
                .car(carService.findCarById(dto.carCarBrand()))
                .driverLicenseNumber(dto.driverLicense())
                .status(CarOrderStatus.NEW)
                .build();

        return carOrderMapper.toDto(carOrderRepository.save(carOrder));
    }

    @Transactional(readOnly = true)
    public List<CarOrderDto> findCardOrdersPersonID(String personID) {
        return carOrderRepository.findAllByBuyer_Name(personID)
                .stream().map(carOrderMapper::toDto).toList();
    }

    @Transactional
    public CarOrderDto approveDriverLicense(UUID id, boolean approved) {
        var carOrder = carOrderRepository.findById(id).orElseThrow();
        carOrder.setDriverLicenceApproved(approved);
        return carOrderMapper.toDto(carOrderRepository.save(carOrder));
    }

    @Transactional
    public CarOrderDto approveLoan(UUID id, boolean approved) {
        var carOrder = carOrderRepository.findById(id).orElseThrow();
        carOrder.setBankApproved(approved);
        return carOrderMapper.toDto(carOrderRepository.save(carOrder));
    }

    @Transactional
    public void cancelOrder(UUID id) {
        carOrderRepository.changeStatus(id, CarOrderStatus.CANCELLED.name());
    }

    @Transactional
    public void completeOrder(UUID id) {
        carOrderRepository.changeStatus(id, CarOrderStatus.APPROVED.name());
    }
}
