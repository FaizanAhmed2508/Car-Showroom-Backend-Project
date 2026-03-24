package com.carshowroom.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
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
    public ApiResponse<String> recordSale(SaleRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<Sale> getSaleById(Long saleId) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Sale>> getAllSales() {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Sale>> getSalesByCustomer(Long customerId) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Sale>> getSalesByEmployee(Long employeeId) {
        // TODO: implement
        return null;
    }

}
