package com.carshowroom.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {

    private Long id;
    private Long carId;
    private String carMake;
    private String carModel;
    private Long customerId;
    private String customerName;
    private Long employeeId;
    private String employeeName;
    private Double salePrice;
    private String paymentMethod;
    private String saleDate;

}
