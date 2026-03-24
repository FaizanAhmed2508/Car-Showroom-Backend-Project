package com.carshowroom.model;

import jakarta.persistence.*;
import lombok.*;
import com.carshowroom.utility.CarShowroomUtil;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Double salePrice;

    private String paymentMethod;

    private String saleDate;

    private String createdTime;
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = createdTime;
        saleDate = createdTime;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }

}
