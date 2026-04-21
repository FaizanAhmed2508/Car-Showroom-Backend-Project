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
import com.carshowroom.response.CustomerPurchaseHistoryResponse;
import org.springframework.data.domain.Page;
import com.carshowroom.utility.CarShowroomUtil;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Register a new customer
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerCustomer(
            @Valid @RequestBody CustomerRequest request) {

        ApiResponse<String> response = customerService.registerCustomer(request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Get customer by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(
            @PathVariable Long customerId) {

        ApiResponse<Customer> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(customerId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CUSTOMER_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = customerService.getCustomerById(customerId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get all customers
    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {

        ApiResponse<List<Customer>> response = customerService.getAllCustomers();

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Update customer details
    @PutMapping("/update/{customerId}")
    public ResponseEntity<ApiResponse<String>> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(customerId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CUSTOMER_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = customerService.updateCustomer(customerId, request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Delete customer (soft delete)
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(
            @RequestParam Long customerId) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(customerId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CUSTOMER_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = customerService.deleteCustomer(customerId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Search customer by name
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Customer>>> searchCustomerByName(
            @RequestParam String name) {

        ApiResponse<List<Customer>> response = new ApiResponse<>();

        // Edge case: blank name
        if (name == null || name.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Name cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        List<Customer> customers = customerService.searchCustomerByName(name);

        if (customers == null || customers.isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CUSTOMER_NOT_FOUND);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
        response.setMessage(CarShowroomConstants.CUSTOMERS_FETCHED_SUCCESSFULLY);
        response.setData(customers);

        return ResponseEntity.ok(response);
    }
    // Get customer purchase history
    @GetMapping("/{customerId}/purchase-history")
    public ResponseEntity<ApiResponse<CustomerPurchaseHistoryResponse>> getCustomerPurchaseHistory(
            @PathVariable Long customerId) {

        ApiResponse<CustomerPurchaseHistoryResponse> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(customerId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CUSTOMER_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = customerService.getCustomerPurchaseHistory(customerId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get all customers paginated
    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<Page<Customer>>> getAllCustomersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ApiResponse<Page<Customer>> response =
                customerService.getAllCustomersPaginated(page, size);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
