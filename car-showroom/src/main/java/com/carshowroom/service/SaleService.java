package com.carshowroom.service;

import com.carshowroom.model.Sale;
import com.carshowroom.request.SaleRequest;
import com.carshowroom.response.ApiResponse;

import java.util.List;

public interface SaleService {

    ApiResponse<String> recordSale(SaleRequest request);
    ApiResponse<Sale> getSaleById(Long saleId);
    ApiResponse<List<Sale>> getAllSales();
    ApiResponse<List<Sale>> getSalesByCustomer(Long customerId);
    ApiResponse<List<Sale>> getSalesByEmployee(Long employeeId);

}
