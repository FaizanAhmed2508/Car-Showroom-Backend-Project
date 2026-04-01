package com.carshowroom.service;

import com.carshowroom.model.Customer;
import com.carshowroom.request.CustomerRequest;
import com.carshowroom.response.ApiResponse;

import java.util.List;

public interface CustomerService {

    ApiResponse<String> registerCustomer(CustomerRequest request);
    ApiResponse<Customer> getCustomerById(Long customerId);
    ApiResponse<List<Customer>> getAllCustomers();
    ApiResponse<String> updateCustomer(Long customerId, CustomerRequest request);
    ApiResponse<String> deleteCustomer(Long customerId);
    List<Customer> searchCustomerByName(String name);

}
