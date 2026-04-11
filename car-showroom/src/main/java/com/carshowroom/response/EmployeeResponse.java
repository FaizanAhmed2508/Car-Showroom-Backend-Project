package com.carshowroom.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String employeeRole;
    private String isActive;
    private String createdTime;
    private String updatedTime;

}