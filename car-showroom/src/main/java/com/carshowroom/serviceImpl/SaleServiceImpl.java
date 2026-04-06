package com.carshowroom.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.exception.CarNotFoundException;
import com.carshowroom.exception.CustomerNotFoundException;
import com.carshowroom.exception.EmployeeNotFoundException;
import com.carshowroom.exception.SaleNotFoundException;
import com.carshowroom.model.Car;
import com.carshowroom.model.Customer;
import com.carshowroom.model.Employee;
import com.carshowroom.model.Sale;
import com.carshowroom.repository.CarRepository;
import com.carshowroom.repository.CustomerRepository;
import com.carshowroom.repository.EmployeeRepository;
import com.carshowroom.repository.SaleRepository;
import com.carshowroom.request.SaleRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public ApiResponse<String> recordSale(SaleRequest request) {

        // Edge Case 1: Check car exists
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new CarNotFoundException(
                        CarShowroomConstants.CAR_NOT_FOUND_WITH_ID + request.getCarId()));

        // Edge Case 2: Check car is not already sold
        if (CarShowroomConstants.CAR_STATUS_SOLD.equals(car.getCarStatus())) {
            return ApiResponse.<String>builder()
                    .status(CarShowroomConstants.STATUS_FAILURE)
                    .message(CarShowroomConstants.CAR_ALREADY_SOLD)
                    .data(null)
                    .build();
        }

        // Edge Case 3: Check customer exists
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        CarShowroomConstants.CUSTOMER_NOT_FOUND_WITH_ID + request.getCustomerId()));

        // Edge Case 4: Employee is optional but if provided must exist
        Employee employee = null;
        if (request.getEmployeeId() != null) {
            employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            CarShowroomConstants.EMPLOYEE_NOT_FOUND_WITH_ID + request.getEmployeeId()));
        }

        // Build and save sale
        Sale sale = Sale.builder()
                .car(car)
                .customer(customer)
                .employee(employee)
                .salePrice(request.getSalePrice())
                .paymentMethod(request.getPaymentMethod())
                .build();

        saleRepository.save(sale);

        // Mark car as SOLD after recording the sale
        car.setCarStatus(CarShowroomConstants.CAR_STATUS_SOLD);
        carRepository.save(car);

        return ApiResponse.<String>builder()
                .status(CarShowroomConstants.STATUS_SUCCESS)
                .message(CarShowroomConstants.SALE_RECORDED_SUCCESSFULLY)
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<Sale> getSaleById(Long saleId) {

        // Fetch sale
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException(
                        CarShowroomConstants.SALE_NOT_FOUND_WITH_ID + saleId));

        // Return response
        return new ApiResponse<>(
                CarShowroomConstants.STATUS_SUCCESS,
                CarShowroomConstants.SALE_FETCHED_SUCCESSFULLY,
                sale
        );
    }

    @Override
    public ApiResponse<List<Sale>> getAllSales() {

        ApiResponse<List<Sale>> response = new ApiResponse<>();

        try {

            List<Sale> sales = saleRepository.findAll();

            if (sales.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_SALES_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.SALES_FETCHED_SUCCESSFULLY);
            response.setData(sales);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<List<Sale>> getSalesByCustomer(Long customerId) {

        ApiResponse<List<Sale>> response = new ApiResponse<>();

        try {

            // Check customer exists first
            customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException(
                            CarShowroomConstants.CUSTOMER_NOT_FOUND_WITH_ID + customerId));

            List<Sale> sales = saleRepository.findByCustomerId(customerId);

            if (sales.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_SALES_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.SALES_FETCHED_SUCCESSFULLY);
            response.setData(sales);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<List<Sale>> getSalesByEmployee(Long employeeId) {

        ApiResponse<List<Sale>> response = new ApiResponse<>();

        try {

            // Check employee exists first
            employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            CarShowroomConstants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

            List<Sale> sales = saleRepository.findByEmployeeId(employeeId);

            if (sales.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_SALES_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.SALES_FETCHED_SUCCESSFULLY);
            response.setData(sales);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

}