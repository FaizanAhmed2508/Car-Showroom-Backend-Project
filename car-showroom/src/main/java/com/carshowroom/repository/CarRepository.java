package com.carshowroom.repository;

import com.carshowroom.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByVin(String vin);
    List<Car> findByCarStatus(String carStatus);
    List<Car> findByMakeIgnoreCase(String make);
    List<Car> findByMakeIgnoreCaseAndModelIgnoreCase(String make, String model);
    // Add custom query methods here

}
