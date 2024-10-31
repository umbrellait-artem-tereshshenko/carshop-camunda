package com.umbrellait.carshop_camunda.controller.dto;

import java.util.UUID;

public record RequestForApprovalDto(UUID id, CarOrderDto carOrder,
                                    CarOrderDto.PersonDto person, boolean approved) {
}
