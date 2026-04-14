package com.carshowroom.service;

import com.carshowroom.model.Sale;
import com.carshowroom.request.SaleRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.response.SalesReportResponse;

import java.util.List;
import java.util.Map;

public interface SaleService {

    ApiResponse<String> recordSale(SaleRequest request);
    ApiResponse<Sale> getSaleById(Long saleId);
    ApiResponse<List<Sale>> getAllSales();
    ApiResponse<List<Sale>> getSalesByCustomer(Long customerId);
    ApiResponse<List<Sale>> getSalesByEmployee(Long employeeId);
    ApiResponse<SalesReportResponse> getSalesReport();
    ApiResponse<Map<String, Object>> getEmployeeSalesReport(Long employeeId);
    ApiResponse<List<Sale>> getSalesByPaymentMethod(String paymentMethod);

}