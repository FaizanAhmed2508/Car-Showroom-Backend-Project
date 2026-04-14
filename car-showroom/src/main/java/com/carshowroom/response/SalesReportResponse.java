package com.carshowroom.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesReportResponse {

    private Long totalSales;
    private Double totalRevenue;
    private Double averageSalePrice;
    private Double highestSalePrice;
    private Double lowestSalePrice;
    private Long totalCarsSold;

}