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
import com.carshowroom.response.CustomerPurchaseHistoryResponse;
import com.carshowroom.repository.SaleRepository;
import com.carshowroom.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Override
    public ApiResponse<String> registerCustomer(CustomerRequest request) {

        // Edge Case 1: Check email already exists
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            return ApiResponse.<String>builder()
                    .status(CarShowroomConstants.STATUS_FAILURE)
                    .message(CarShowroomConstants.CUSTOMER_EMAIL_EXISTS)
                    .data(null)
                    .build();
        }

        // Build and save customer
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .deleted(false)
                .build();

        customerRepository.save(customer);

        return ApiResponse.<String>builder()
                .status(CarShowroomConstants.STATUS_SUCCESS)
                .message(CarShowroomConstants.CUSTOMER_REGISTERED_SUCCESSFULLY)
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<Customer> getCustomerById(Long customerId) {

        // Fetch customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        CarShowroomConstants.CUSTOMER_NOT_FOUND_WITH_ID + customerId));

        // Return response
        return new ApiResponse<>(
                CarShowroomConstants.STATUS_SUCCESS,
                CarShowroomConstants.CUSTOMER_FETCHED_SUCCESSFULLY,
                customer
        );
    }

    @Override
    public ApiResponse<List<Customer>> getAllCustomers() {

        ApiResponse<List<Customer>> response = new ApiResponse<>();

        try {

            // Only fetch non-deleted customers
            List<Customer> customers = customerRepository.findByDeleted(false);

            if (customers.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_CUSTOMERS_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CUSTOMERS_FETCHED_SUCCESSFULLY);
            response.setData(customers);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> updateCustomer(Long customerId, CustomerRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException(
                            CarShowroomConstants.CUSTOMER_NOT_FOUND_WITH_ID + customerId));

            // Check if new email belongs to a different customer
            if (customerRepository.findByEmailAndIdNot(request.getEmail(), customerId).isPresent()) {
                throw new RuntimeException(CarShowroomConstants.CUSTOMER_EMAIL_EXISTS);
            }

            customer.setFirstName(request.getFirstName());
            customer.setLastName(request.getLastName());
            customer.setEmail(request.getEmail());
            customer.setPhone(request.getPhone());
            customer.setAddress(request.getAddress());

            customerRepository.save(customer);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CUSTOMER_UPDATED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> deleteCustomer(Long customerId) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException(
                            CarShowroomConstants.CUSTOMER_NOT_FOUND_WITH_ID + customerId));

            // Soft delete — same pattern as car
            customer.setDeleted(true);
            customerRepository.save(customer);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CUSTOMER_DELETED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {

        if (name == null) {
            return List.of();
        }

        System.out.println(customerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name));
        return customerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }
    @Override
    public ApiResponse<CustomerPurchaseHistoryResponse> getCustomerPurchaseHistory(
            Long customerId) {

        ApiResponse<CustomerPurchaseHistoryResponse> response = new ApiResponse<>();

        try {

            // Check customer exists
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException(
                            CarShowroomConstants.CUSTOMER_NOT_FOUND_WITH_ID + customerId));

            // Get all sales for this customer
            List<Sale> sales = saleRepository.findByCustomerId(customerId);

            if (sales.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_PURCHASE_HISTORY_FOUND);
                response.setData(null);
                return response;
            }

            // Build purchase details list
            List<CustomerPurchaseHistoryResponse.PurchaseDetail> purchases = sales.stream()
                    .map(sale -> CustomerPurchaseHistoryResponse.PurchaseDetail.builder()
                            .saleId(sale.getId())
                            .carId(sale.getCar().getId())
                            .carMake(sale.getCar().getMake())
                            .carModel(sale.getCar().getModel())
                            .carYear(sale.getCar().getYear())
                            .salePrice(sale.getSalePrice())
                            .paymentMethod(sale.getPaymentMethod())
                            .saleDate(sale.getSaleDate())
                            .build())
                    .collect(Collectors.toList());

            // Calculate totals
            Double totalSpent = sales.stream()
                    .mapToDouble(Sale::getSalePrice)
                    .sum();

            // Build response
            CustomerPurchaseHistoryResponse history =
                    CustomerPurchaseHistoryResponse.builder()
                            .customerId(customer.getId())
                            .customerFirstName(customer.getFirstName())
                            .customerLastName(customer.getLastName())
                            .customerEmail(customer.getEmail())
                            .totalCarsPurchased((long) sales.size())
                            .totalAmountSpent(totalSpent)
                            .purchases(purchases)
                            .build();

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.PURCHASE_HISTORY_FETCHED_SUCCESSFULLY);
            response.setData(history);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<Page<Customer>> getAllCustomersPaginated(int page, int size) {

        ApiResponse<Page<Customer>> response = new ApiResponse<>();

        try {

            Pageable pageable = PageRequest.of(page, size,
                    Sort.by("createdTime").descending());

            Page<Customer> customers = customerRepository.findAll(pageable);

            if (customers.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_CUSTOMERS_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CUSTOMERS_FETCHED_SUCCESSFULLY);
            response.setData(customers);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

}