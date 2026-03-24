package com.carshowroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.model.Employee;
import com.carshowroom.request.EmployeeRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Add a new employee
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        // TODO: implement
        return null;
    }

    // Get employee by ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable Long employeeId) {
        // TODO: implement
        return null;
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {
        // TODO: implement
        return null;
    }

    // Update employee details
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<ApiResponse<String>> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody EmployeeRequest request) {
        // TODO: implement
        return null;
    }

    // Delete employee
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@RequestParam Long employeeId) {
        // TODO: implement
        return null;
    }

}
