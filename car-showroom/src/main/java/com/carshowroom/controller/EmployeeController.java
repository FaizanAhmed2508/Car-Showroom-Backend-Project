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
import com.carshowroom.utility.CarShowroomUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Employee", description = "Employee management endpoints")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Add a new employee
    @Operation(summary = "Add a new employee")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        ApiResponse<String> response = employeeService.addEmployee(request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Get employee by ID
    @Operation(summary = "Get employee by ID")
    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(
            @PathVariable Long employeeId) {

        ApiResponse<Employee> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(employeeId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = employeeService.getEmployeeById(employeeId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get all employees
    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {

        ApiResponse<List<Employee>> response = employeeService.getAllEmployees();

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Update employee details
    @Operation(summary = "Update employee details")
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<ApiResponse<String>> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody EmployeeRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(employeeId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = employeeService.updateEmployee(employeeId, request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Delete employee (soft delete)
    @Operation(summary = "Delete employee")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(
            @RequestParam Long employeeId) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(employeeId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = employeeService.deleteEmployee(employeeId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Search employee by name
    @Operation(summary = "Search employee by name")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Employee>>> searchEmployeeByName(
            @RequestParam String name) {

        ApiResponse<List<Employee>> response = new ApiResponse<>();

        // Edge case: blank name
        if (name == null || name.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Name cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        List<Employee> employees = employeeService.searchEmployeeByName(name);

        if (employees == null || employees.isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.EMPLOYEE_NOT_FOUND);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
        response.setMessage(CarShowroomConstants.EMPLOYEES_FETCHED_SUCCESSFULLY);
        response.setData(employees);

        return ResponseEntity.ok(response);
    }

}
