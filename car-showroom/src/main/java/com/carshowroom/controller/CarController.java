package com.carshowroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.model.Car;
import com.carshowroom.request.CarRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    // Add a new car
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCar(@Valid @RequestBody CarRequest request) {
        // TODO: implement
        return null;
    }

    // Get car by ID
    @GetMapping("/{carId}")
    public ResponseEntity<ApiResponse<Car>> getCarById(@PathVariable Long carId) {
        // TODO: implement
        return null;
    }

    // Get car by VIN
    @GetMapping("/vin/{vin}")
    public ResponseEntity<ApiResponse<Car>> getCarByVin(@PathVariable String vin) {
        // TODO: implement
        return null;
    }

    // Get all cars
    @GetMapping
    public ResponseEntity<ApiResponse<List<Car>>> getAllCars() {
        // TODO: implement
        return null;
    }

    // Get cars by status
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Car>>> getCarsByStatus(@PathVariable String status) {
        // TODO: implement
        return null;
    }

    // Update car details
    @PutMapping("/update/{carId}")
    public ResponseEntity<ApiResponse<String>> updateCar(
            @PathVariable Long carId,
            @RequestBody CarRequest request) {
        // TODO: implement
        return null;
    }

    // Delete car (soft delete)
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteCar(@RequestParam Long carId) {
        // TODO: implement
        return null;
    }

    // Update car status
    @PutMapping("/{carId}/status")
    public ResponseEntity<ApiResponse<String>> updateCarStatus(
            @PathVariable Long carId,
            @RequestParam String status) {
        // TODO: implement
        return null;
    }

    // Search car by make
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Car>>> searchCarByMake(@RequestParam String make) {
        // TODO: implement
        return null;
    }

}
