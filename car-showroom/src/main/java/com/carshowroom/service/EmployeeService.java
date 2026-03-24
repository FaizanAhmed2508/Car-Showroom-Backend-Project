package com.carshowroom.service;

import com.carshowroom.model.Employee;
import com.carshowroom.request.EmployeeRequest;
import com.carshowroom.response.ApiResponse;

import java.util.List;

public interface EmployeeService {

    ApiResponse<String> addEmployee(EmployeeRequest request);
    ApiResponse<Employee> getEmployeeById(Long employeeId);
    ApiResponse<List<Employee>> getAllEmployees();
    ApiResponse<String> updateEmployee(Long employeeId, EmployeeRequest request);
    ApiResponse<String> deleteEmployee(Long employeeId);
    // Add more methods as needed

}
