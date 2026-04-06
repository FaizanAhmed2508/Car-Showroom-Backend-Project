package com.carshowroom.repository;

import com.carshowroom.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomerId(Long customerId);
    List<Sale> findByEmployeeId(Long employeeId);

    // Get all sales for a specific car
    List<Sale> findByCarId(Long carId);

    // Check if a car has already been sold
    boolean existsByCarId(Long carId);

}
