package com.carshowroom.model;

import jakarta.persistence.*;
import lombok.*;
import com.carshowroom.utility.CarShowroomUtil;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(unique = true, nullable = false)
    private String vin;

    private Double price;

    private Integer mileage;

    private String fuelType;

    private String transmission;

    @Column(name = "car_status")
    private String carStatus;

    private String color;

    private String description;

    // Enums as inner classes — senior's style
    public enum FuelType {
        PETROL, DIESEL, ELECTRIC, HYBRID, CNG
    }

    public enum TransmissionType {
        MANUAL, AUTOMATIC, SEMI_AUTOMATIC
    }

    public enum CarStatus {
        AVAILABLE, SOLD, RESERVED, UNDER_MAINTENANCE
    }

    private String createdTime;
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = createdTime;
        carStatus = CarStatus.AVAILABLE.name();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }

}
