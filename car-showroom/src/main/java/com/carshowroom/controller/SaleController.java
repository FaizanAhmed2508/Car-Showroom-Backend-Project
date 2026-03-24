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
    public ResponseEntity<ApiResponse<String>> recordSale(@Valid @RequestBody SaleRequest request) {
        // TODO: implement
        return null;
    }

    // Get sale by ID
    @GetMapping("/{saleId}")
    public ResponseEntity<ApiResponse<Sale>> getSaleById(@PathVariable Long saleId) {
        // TODO: implement
        return null;
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<ApiResponse<List<Sale>>> getAllSales() {
        // TODO: implement
        return null;
    }

    // Get sales by customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<Sale>>> getSalesByCustomer(@PathVariable Long customerId) {
        // TODO: implement
        return null;
    }

    // Get sales by employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<Sale>>> getSalesByEmployee(@PathVariable Long employeeId) {
        // TODO: implement
        return null;
    }

}
