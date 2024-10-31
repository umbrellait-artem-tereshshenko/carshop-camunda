package com.umbrellait.carshop_camunda.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "request_for_approval")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestForApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "car_order_id", nullable = false)
    private CarOrder carOrder;

    @ManyToOne
    @JoinColumn(name = "person_name", nullable = false)
    private Person person;

    @Column(name = "approved")
    private boolean approved = false;

}
