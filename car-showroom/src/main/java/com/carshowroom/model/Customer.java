package com.carshowroom.model;

import jakarta.persistence.*;
import lombok.*;
import com.carshowroom.utility.CarShowroomUtil;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String address;

    private Boolean deleted = false;

    private String createdTime;
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = createdTime;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }

}
