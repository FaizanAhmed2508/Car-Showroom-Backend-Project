package com.carshowroom.repository;

import com.carshowroom.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomerId(Long customerId);
    List<Sale> findByEmployeeId(Long employeeId);
    List<Sale> findByCarId(Long carId);
    boolean existsByCarId(Long carId);

    // Total revenue from all sales
    @Query("SELECT SUM(s.salePrice) FROM Sale s")
    Double getTotalRevenue();

    // Average sale price
    @Query("SELECT AVG(s.salePrice) FROM Sale s")
    Double getAverageSalePrice();

    // Highest sale price
    @Query("SELECT MAX(s.salePrice) FROM Sale s")
    Double getHighestSalePrice();

    // Lowest sale price
    @Query("SELECT MIN(s.salePrice) FROM Sale s")
    Double getLowestSalePrice();

    // Total revenue by employee
    @Query("SELECT SUM(s.salePrice) FROM Sale s WHERE s.employee.id = :employeeId")
    Double getTotalRevenueByEmployee(@Param("employeeId") Long employeeId);

    // Count sales by employee
    @Query("SELECT COUNT(s) FROM Sale s WHERE s.employee.id = :employeeId")
    Long countSalesByEmployee(@Param("employeeId") Long employeeId);

    // Get sales by payment method
    List<Sale> findByPaymentMethod(String paymentMethod);

    // Count sales by payment method
    @Query("SELECT COUNT(s) FROM Sale s WHERE s.paymentMethod = :paymentMethod")
    Long countByPaymentMethod(@Param("paymentMethod") String paymentMethod);

}
