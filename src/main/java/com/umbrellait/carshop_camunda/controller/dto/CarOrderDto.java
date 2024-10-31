package com.umbrellait.carshop_camunda.controller.dto;

import com.umbrellait.carshop_camunda.model.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link CarOrder}
 */

@Getter
@Setter
@Builder
public final class CarOrderDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private UUID id;
    private CarDto car;
    private CarOrderStatus status;
    private Set<AdditionalCarParts> additionalCarParts;
    private boolean loan;
    private boolean bankApproved;
    private String driverLicenseNumber;
    private boolean driverLicenceApproved;
    private PersonDto buyer;
    private PersonDto manager;
    private long totalPrice;

    /**
     *
     */
    public CarOrderDto(UUID id, CarDto car, CarOrderStatus status, Set<AdditionalCarParts> additionalCarParts,
                       boolean loan, boolean bankApproved, String driverLicenseNumber,
                       boolean driverLicenceApproved, PersonDto buyer, PersonDto manager, long totalPrice) {
        this.id = id;
        this.car = car;
        this.status = status;
        this.additionalCarParts = additionalCarParts;
        this.loan = loan;
        this.bankApproved = bankApproved;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverLicenceApproved = driverLicenceApproved;
        this.buyer = buyer;
        this.manager = manager;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CarOrderDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.car, that.car) &&
                Objects.equals(this.status, that.status) &&
                Objects.equals(this.additionalCarParts, that.additionalCarParts) &&
                this.loan == that.loan &&
                this.bankApproved == that.bankApproved &&
                Objects.equals(this.driverLicenseNumber, that.driverLicenseNumber) &&
                this.driverLicenceApproved == that.driverLicenceApproved &&
                Objects.equals(this.buyer, that.buyer) &&
                Objects.equals(this.manager, that.manager) &&
                this.totalPrice == that.totalPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, status, additionalCarParts, loan, bankApproved, driverLicenseNumber, driverLicenceApproved, buyer, manager, totalPrice);
    }

    @Override
    public String toString() {
        return "CarOrderDto[" +
                "id=" + id + ", " +
                "car=" + car + ", " +
                "status=" + status + ", " +
                "additionalCarParts=" + additionalCarParts + ", " +
                "isLoan=" + loan + ", " +
                "bankApproved=" + bankApproved + ", " +
                "driverLicenseNumber=" + driverLicenseNumber + ", " +
                "driverLicenceApproved=" + driverLicenceApproved + ", " +
                "buyer=" + buyer + ", " +
                "manager=" + manager + ", " +
                "totalPrice=" + totalPrice + ']';
    }

    /**
     * DTO for {@link Car}
     */
    public record CarDto(CarBrand carBrand, long price) implements Serializable {
    }

    /**
     * DTO for {@link Person}
     */
    public record PersonDto(String name) implements Serializable {
    }
}