package com.carshowroom.model;

import jakarta.persistence.*;
import lombok.*;
import com.carshowroom.utility.CarShowroomUtil;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(name = "employee_role")
    private String employeeRole;

    @Column(name = "is_active")
    private String isActive;

    private String createdTime;
    private String updatedTime;

    public enum EmployeeRole {
        SALESPERSON, MANAGER, TECHNICIAN, RECEPTIONIST
    }

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
