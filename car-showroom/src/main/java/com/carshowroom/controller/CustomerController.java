package com.carshowroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.model.Customer;
import com.carshowroom.request.CustomerRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Register a new customer
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerCustomer(@Valid @RequestBody CustomerRequest request) {
        // TODO: implement
        return null;
    }

    // Get customer by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long customerId) {
        // TODO: implement
        return null;
    }

    // Get all customers
    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        // TODO: implement
        return null;
    }

    // Update customer details
    @PutMapping("/update/{customerId}")
    public ResponseEntity<ApiResponse<String>> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequest request) {
        // TODO: implement
        return null;
    }

    // Delete customer
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@RequestParam Long customerId) {
        // TODO: implement
        return null;
    }

}
