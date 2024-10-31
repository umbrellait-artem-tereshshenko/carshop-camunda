package com.umbrellait.carshop_camunda.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Car order entity class for aggregating car orders process variables:
 * specified car model, total price, buyer, assigned manager, etc.
 *
 *
 * @author artem.tereshchenko
 *
 */

@Entity
@Table(name = "car_order")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CarOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CarOrderStatus status;

    @ElementCollection
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<AdditionalCarParts> additionalCarParts = new LinkedHashSet<>();

    @Column(name = "is_loan", nullable = false, columnDefinition = "boolean default false")
    private boolean loan = false;

    @Column(name = "bank_approved", nullable = false)
    private boolean bankApproved = false;

    @Column(name = "driver_license_number", nullable = false)
    private String driverLicenseNumber;

    @Column(name = "driver_licence_approved", nullable = false)
    private boolean driverLicenceApproved = false;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Person buyer;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Person manager;

    @Column(name = "total_price", nullable = false)
    private long totalPrice;

}
