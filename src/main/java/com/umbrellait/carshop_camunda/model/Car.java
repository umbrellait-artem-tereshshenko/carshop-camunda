package com.umbrellait.carshop_camunda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Car entity class storing basic car information: brand and price in smallest
 * monetary unit.
 *
 * @author artem.tereshchenko
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Car implements Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "car_brand", nullable = false)
    private CarBrand carBrand;

    @Column(name = "price", nullable = false)
    private long price;

}
