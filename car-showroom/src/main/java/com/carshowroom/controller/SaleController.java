package com.carshowroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.model.Sale;
import com.carshowroom.request.SaleRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    // Record a new sale
    @PostMapping("/record")
    public ResponseEntity<ApiResponse<String>> recordSale(
            @Valid @RequestBody SaleRequest request) {

        ApiResponse<String> response = saleService.recordSale(request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Get sale by ID
    @GetMapping("/{saleId}")
    public ResponseEntity<ApiResponse<Sale>> getSaleById(@PathVariable Long saleId) {

        ApiResponse<Sale> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (saleId <= 0) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.SALE_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = saleService.getSaleById(saleId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<ApiResponse<List<Sale>>> getAllSales() {

        ApiResponse<List<Sale>> response = saleService.getAllSales();

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get sales by customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<Sale>>> getSalesByCustomer(
            @PathVariable Long customerId) {

        ApiResponse<List<Sale>> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (customerId <= 0) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CUSTOMER_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = saleService.getSalesByCustomer(customerId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get sales by employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<Sale>>> getSalesByEmployee(
            @PathVariable Long employeeId) {

        ApiResponse<List<Sale>> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (employeeId <= 0) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = saleService.getSalesByEmployee(employeeId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}