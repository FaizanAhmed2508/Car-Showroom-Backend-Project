package com.carshowroom.service;

import com.carshowroom.model.Car;
import com.carshowroom.request.CarRequest;
import com.carshowroom.response.ApiResponse;

import java.util.List;
import java.util.Map;

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
    // Add more methods as needed

}
