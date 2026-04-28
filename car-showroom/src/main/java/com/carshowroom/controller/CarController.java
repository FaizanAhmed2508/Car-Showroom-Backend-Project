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
import org.springframework.data.domain.Page;
import com.carshowroom.utility.CarShowroomUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Car", description = "Car management endpoints")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    // Add a new car
    @Operation(summary = "Add a new car")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCar(@Valid @RequestBody CarRequest request) {

        ApiResponse<String> response = carService.addCar(request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Get car by ID
    @Operation(summary = "Get car by ID")
    @GetMapping("/{carId}")
    public ResponseEntity<ApiResponse<Car>> getCarById(@PathVariable Long carId) {

        ApiResponse<Car> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(carId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = carService.getCarById(carId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get car by VIN
    @Operation(summary = "Get car by VIN")
    @GetMapping("/vin/{vin}")
    public ResponseEntity<ApiResponse<Car>> getCarByVin(@PathVariable String vin) {

        ApiResponse<Car> response = new ApiResponse<>();

        // Edge case: blank VIN
        if (vin == null || vin.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("VIN cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = carService.getCarByVin(vin);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get all cars
    @Operation(summary = "Get all cars")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Car>>> getAllCars() {

        ApiResponse<List<Car>> response = carService.getAllCars();

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Get cars by status
    @Operation(summary = "Get cars by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Car>>> getCarsByStatus(@PathVariable String status) {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        // Edge case: blank status
        if (status == null || status.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Status cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = carService.getCarsByStatus(status);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Update car details
    @Operation(summary = "Update car details")
    @PutMapping("/update/{carId}")
    public ResponseEntity<ApiResponse<String>> updateCar(
            @PathVariable Long carId,
            @RequestBody CarRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(carId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = carService.updateCar(carId, request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Delete car (soft delete)
    @Operation(summary = "Delete car")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteCar(@RequestParam Long carId) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (carId == null || carId <= 0) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.INVALID_CAR_ID);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = carService.deleteCar(carId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Update car status
    @Operation(summary = "Update car status")
    @PutMapping("/{carId}/status")
    public ResponseEntity<ApiResponse<String>> updateCarStatus(
            @PathVariable Long carId,
            @RequestParam String status) {

        ApiResponse<String> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(carId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_ID_CANNOT_BE_NULL_OR_NEGATIVE);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        // Edge case: blank status
        if (status == null || status.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Status cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = carService.updateCarStatus(carId, status);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Search car by make
    @Operation(summary = "Search cars by make")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Car>>> searchCarByMake(@RequestParam String make) {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        // Edge case: blank make
        if (make == null || make.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Make cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        List<Car> cars = carService.searchCarByMake(make);

        if (cars == null || cars.isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_NOT_FOUND);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
        response.setMessage(CarShowroomConstants.CARS_FETCHED_SUCCESSFULLY);
        response.setData(cars);

        return ResponseEntity.ok(response);
    }
    // Search cars by price range
    @Operation(summary = "Search cars by price range")
    @GetMapping("/search/price")
    public ResponseEntity<ApiResponse<List<Car>>> searchCarsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        // Edge case: invalid price range
        if (CarShowroomUtil.isInvalidPriceRange(minPrice, maxPrice)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Invalid price range");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        if (minPrice > maxPrice) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Min price cannot be greater than max price");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        List<Car> cars = carService.searchCarsByPriceRange(minPrice, maxPrice);

        if (cars == null || cars.isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_NOT_FOUND);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
        response.setMessage(CarShowroomConstants.CAR_SEARCH_RESULTS_FETCHED);
        response.setData(cars);

        return ResponseEntity.ok(response);
    }

    // Search cars by year range
    @Operation(summary = "Search cars by year range")
    @GetMapping("/search/year")
    public ResponseEntity<ApiResponse<List<Car>>> searchCarsByYearRange(
            @RequestParam Integer startYear,
            @RequestParam Integer endYear) {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        // Edge case: invalid year range
        if (CarShowroomUtil.isInvalidYearRange(startYear, endYear)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Year range cannot be null");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        if (startYear > endYear) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Start year cannot be greater than end year");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        List<Car> cars = carService.searchCarsByYearRange(startYear, endYear);

        if (cars == null || cars.isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_NOT_FOUND);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
        response.setMessage(CarShowroomConstants.CAR_SEARCH_RESULTS_FETCHED);
        response.setData(cars);

        return ResponseEntity.ok(response);
    }

    // Search cars by fuel type
    @Operation(summary = "Search cars by fuel type")
    @GetMapping("/search/fuel")
    public ResponseEntity<ApiResponse<List<Car>>> searchCarsByFuelType(
            @RequestParam String fuelType) {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        // Edge case: blank fuel type
        if (fuelType == null || fuelType.trim().isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage("Fuel type cannot be blank");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        List<Car> cars = carService.searchCarsByFuelType(fuelType);

        if (cars == null || cars.isEmpty()) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.CAR_NOT_FOUND);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
        response.setMessage(CarShowroomConstants.CAR_SEARCH_RESULTS_FETCHED);
        response.setData(cars);

        return ResponseEntity.ok(response);
    }
    // Get all cars paginated
    @Operation(summary = "Get all cars paginated")
    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<Page<Car>>> getAllCarsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ApiResponse<Page<Car>> response =
                carService.getAllCarsPaginated(page, size);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
