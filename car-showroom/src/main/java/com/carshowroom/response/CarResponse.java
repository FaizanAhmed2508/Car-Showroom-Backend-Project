package com.carshowroom.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Long carId;
    private String make;
    private String model;
    private Integer year;
    private String vin;
    private Double price;
    private Integer mileage;
    private String fuelType;
    private String transmission;
    private String carStatus;
    private String color;
    private String description;
    private String createdTime;
    private String updatedTime;

}