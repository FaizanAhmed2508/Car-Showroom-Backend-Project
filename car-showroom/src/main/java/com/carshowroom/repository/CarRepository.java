package com.carshowroom.repository;

import com.carshowroom.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByVin(String vin);
    List<Car> findByCarStatus(String carStatus);
    List<Car> findByMakeIgnoreCase(String make);
    List<Car> findByMakeIgnoreCaseAndModelIgnoreCase(String make, String model);

    // Search by year range
    List<Car> findByYearBetween(Integer startYear, Integer endYear);

    // Search by price range
    List<Car> findByPriceBetween(Double minPrice, Double maxPrice);

    // Search by make containing keyword (like %keyword%)
    List<Car> findByMakeContainingIgnoreCase(String keyword);

    // Get all cars excluding soft deleted ones
    List<Car> findByCarStatusNot(String carStatus);

    // Check if VIN exists for a different car (used in update)
    @Query("SELECT c FROM Car c WHERE c.vin = :vin AND c.id != :carId")
    Optional<Car> findByVinAndIdNot(@Param("vin") String vin, @Param("carId") Long carId);

}