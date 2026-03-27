package com.carshowroom.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequest {

    private Long carId;

    @NotBlank(message = "Make must not be blank")
    private String make;

    @NotBlank(message = "Model must not be blank")
    private String model;

    @NotNull(message = "Year must not be null")
    private Integer year;

    @NotBlank(message = "VIN must not be blank")
    private String vin;

    @NotNull(message = "Price must not be null")
    private Double price;

    private Integer mileage;

    private String fuelType;

    private String transmission;

    private String color;

    private String description;

}