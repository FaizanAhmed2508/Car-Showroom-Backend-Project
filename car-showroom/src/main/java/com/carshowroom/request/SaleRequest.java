package com.carshowroom.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleRequest {

    @NotNull(message = "Car ID must not be null")
    private Long carId;

    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    private Long employeeId;

    @NotNull(message = "Sale price must not be null")
    private Double salePrice;

    private String paymentMethod;   // CASH, FINANCE, LEASE

}
