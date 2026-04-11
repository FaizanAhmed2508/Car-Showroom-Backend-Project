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

    private Long saleId;
    private Long carId;
    private String carMake;
    private String carModel;
    private Integer carYear;
    private String carVin;
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private Double salePrice;
    private String paymentMethod;
    private String saleDate;
    private String createdTime;

}