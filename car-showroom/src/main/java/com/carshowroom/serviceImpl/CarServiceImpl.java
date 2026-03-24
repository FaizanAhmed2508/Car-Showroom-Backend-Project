package com.carshowroom.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.exception.CarNotFoundException;
import com.carshowroom.model.Car;
import com.carshowroom.repository.CarRepository;
import com.carshowroom.request.CarRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public ApiResponse<String> addCar(CarRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<Car> getCarById(Long carId) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<Car> getCarByVin(String vin) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Car>> getAllCars() {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Car>> getCarsByStatus(String status) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> updateCar(Long carId, CarRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> deleteCar(Long carId) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> updateCarStatus(Long carId, String status) {
        // TODO: implement
        return null;
    }

    @Override
    public List<Car> searchCarByMake(String make) {
        // TODO: implement
        return null;
    }

}
