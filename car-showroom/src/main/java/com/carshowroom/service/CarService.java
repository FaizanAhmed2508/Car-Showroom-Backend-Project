package com.carshowroom.service;

import com.carshowroom.model.Car;
import com.carshowroom.request.CarRequest;
import com.carshowroom.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    ApiResponse<String> addCar(CarRequest request);
    ApiResponse<Car> getCarById(Long carId);
    ApiResponse<Car> getCarByVin(String vin);
    ApiResponse<List<Car>> getAllCars();
    ApiResponse<List<Car>> getCarsByStatus(String status);
    ApiResponse<String> updateCar(Long carId, CarRequest request);
    ApiResponse<String> deleteCar(Long carId);
    ApiResponse<String> updateCarStatus(Long carId, String status);
    List<Car> searchCarByMake(String make);
    List<Car> searchCarsByPriceRange(Double minPrice, Double maxPrice);
    List<Car> searchCarsByYearRange(Integer startYear, Integer endYear);
    List<Car> searchCarsByFuelType(String fuelType);
    ApiResponse<Page<Car>> getAllCarsPaginated(int page, int size);

}