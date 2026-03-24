package com.carshowroom.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.exception.CustomerNotFoundException;
import com.carshowroom.model.Customer;
import com.carshowroom.repository.CustomerRepository;
import com.carshowroom.request.CustomerRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public ApiResponse<String> registerCustomer(CustomerRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<Customer> getCustomerById(Long customerId) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Customer>> getAllCustomers() {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> updateCustomer(Long customerId, CustomerRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> deleteCustomer(Long customerId) {
        // TODO: implement
        return null;
    }

}
