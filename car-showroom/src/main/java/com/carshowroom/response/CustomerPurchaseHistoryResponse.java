package com.carshowroom.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPurchaseHistoryResponse {

    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private Long totalCarsPurchased;
    private Double totalAmountSpent;
    private java.util.List<PurchaseDetail> purchases;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PurchaseDetail {
        private Long saleId;
        private Long carId;
        private String carMake;
        private String carModel;
        private Integer carYear;
        private Double salePrice;
        private String paymentMethod;
        private String saleDate;
    }

}