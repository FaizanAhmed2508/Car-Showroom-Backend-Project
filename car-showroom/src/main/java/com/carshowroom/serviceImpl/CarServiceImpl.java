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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private static final Set<String> VALID_CAR_STATUSES = Set.of(
            CarShowroomConstants.CAR_STATUS_AVAILABLE,
            CarShowroomConstants.CAR_STATUS_SOLD,
            CarShowroomConstants.CAR_STATUS_RESERVED,
            CarShowroomConstants.CAR_STATUS_UNDER_MAINTENANCE
    );

    private static final Set<String> VALID_FUEL_TYPES = Set.of(
            "PETROL", "DIESEL", "ELECTRIC", "HYBRID", "CNG"
    );

    @Override
    public ApiResponse<String> addCar(CarRequest request) {

        // Edge Case 1: Check VIN already exists
        if (carRepository.findByVin(request.getVin()).isPresent()) {
            return ApiResponse.<String>builder()
                    .status(CarShowroomConstants.STATUS_FAILURE)
                    .message(CarShowroomConstants.CAR_VIN_EXISTS)
                    .data(null)
                    .build();
        }

        // Build and save car
        Car car = Car.builder()
                .make(request.getMake())
                .model(request.getModel())
                .year(request.getYear())
                .vin(request.getVin())
                .price(request.getPrice())
                .mileage(request.getMileage())
                .fuelType(request.getFuelType())
                .transmission(request.getTransmission())
                .color(request.getColor())
                .description(request.getDescription())
                .build();

        carRepository.save(car);

        return ApiResponse.<String>builder()
                .status(CarShowroomConstants.STATUS_SUCCESS)
                .message(CarShowroomConstants.CAR_ADDED_SUCCESSFULLY)
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<Car> getCarById(Long carId) {

        // Fetch car
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(
                        CarShowroomConstants.CAR_NOT_FOUND_WITH_ID + carId));

        // Return response
        return new ApiResponse<>(
                CarShowroomConstants.STATUS_SUCCESS,
                CarShowroomConstants.CAR_FETCHED_SUCCESSFULLY,
                car
        );
    }

    @Override
    public ApiResponse<Car> getCarByVin(String vin) {

        // Fetch car by VIN
        Car car = carRepository.findByVin(vin)
                .orElseThrow(() -> new CarNotFoundException(
                        CarShowroomConstants.CAR_NOT_FOUND));

        return new ApiResponse<>(
                CarShowroomConstants.STATUS_SUCCESS,
                CarShowroomConstants.CAR_FETCHED_SUCCESSFULLY,
                car
        );
    }

    @Override
    public ApiResponse<List<Car>> getAllCars() {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        try {

            List<Car> cars = carRepository.findAll();

            if (cars.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_CARS_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CARS_FETCHED_SUCCESSFULLY);
            response.setData(cars);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<List<Car>> getCarsByStatus(String status) {

        ApiResponse<List<Car>> response = new ApiResponse<>();

        try {

            List<Car> cars = carRepository.findByCarStatus(status.toUpperCase());

            if (cars.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_CARS_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CARS_FETCHED_SUCCESSFULLY);
            response.setData(cars);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> updateCar(Long carId, CarRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            Car car = carRepository.findById(carId)
                    .orElseThrow(() -> new CarNotFoundException(
                            CarShowroomConstants.CAR_NOT_FOUND_WITH_ID + carId));

            // Check if new VIN belongs to a different car
            if (carRepository.findByVinAndIdNot(request.getVin(), carId).isPresent()) {
                throw new RuntimeException(CarShowroomConstants.CAR_VIN_EXISTS);
            }

            car.setMake(request.getMake());
            car.setModel(request.getModel());
            car.setYear(request.getYear());
            car.setVin(request.getVin());
            car.setPrice(request.getPrice());
            car.setMileage(request.getMileage());
            car.setFuelType(request.getFuelType());
            car.setTransmission(request.getTransmission());
            car.setColor(request.getColor());
            car.setDescription(request.getDescription());

            carRepository.save(car);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CAR_UPDATED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> deleteCar(Long carId) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            Car car = carRepository.findById(carId)
                    .orElseThrow(() -> new CarNotFoundException(
                            CarShowroomConstants.CAR_NOT_FOUND_WITH_ID + carId));

            // Soft delete — mark as inactive instead of removing from DB
            car.setCarStatus(CarShowroomConstants.IN_ACTIVE);
            carRepository.save(car);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CAR_DELETED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> updateCarStatus(Long carId, String status) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            // Validate status value
            if (!VALID_CAR_STATUSES.contains(status.toUpperCase())) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.INVALID_CAR_STATUS);
                response.setData(null);
                return response;
            }

            Car car = carRepository.findById(carId)
                    .orElseThrow(() -> new CarNotFoundException(
                            CarShowroomConstants.CAR_NOT_FOUND_WITH_ID + carId));

            // Skip update if already at same status
            if (status.equalsIgnoreCase(car.getCarStatus())) {
                response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
                response.setMessage(CarShowroomConstants.CAR_STATUS_UPDATED_SUCCESSFULLY);
                response.setData(null);
                return response;
            }

            car.setCarStatus(status.toUpperCase());
            carRepository.save(car);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CAR_STATUS_UPDATED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public List<Car> searchCarByMake(String make) {

        if (make == null) {
            return List.of();
        }

        System.out.println(carRepository.findByMakeIgnoreCase(make));
        return carRepository.findByMakeIgnoreCase(make);
    }
    @Override
    public List<Car> searchCarsByPriceRange(Double minPrice, Double maxPrice) {

        if (minPrice == null || maxPrice == null) {
            return List.of();
        }

        if (minPrice < 0 || maxPrice < 0) {
            return List.of();
        }

        if (minPrice > maxPrice) {
            return List.of();
        }

        System.out.println(carRepository.findByPriceBetween(minPrice, maxPrice));
        return carRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Car> searchCarsByYearRange(Integer startYear, Integer endYear) {

        if (startYear == null || endYear == null) {
            return List.of();
        }

        if (startYear > endYear) {
            return List.of();
        }

        System.out.println(carRepository.findByYearBetween(startYear, endYear));
        return carRepository.findByYearBetween(startYear, endYear);
    }

    @Override
    public List<Car> searchCarsByFuelType(String fuelType) {

        if (fuelType == null) {
            return List.of();
        }

        if (!VALID_FUEL_TYPES.contains(fuelType.toUpperCase())) {
            return List.of();
        }

        System.out.println(carRepository.findByMakeIgnoreCase(fuelType));
        return carRepository.findByFuelTypeIgnoreCase(fuelType);
    }
    @Override
    public ApiResponse<Page<Car>> getAllCarsPaginated(int page, int size) {

        ApiResponse<Page<Car>> response = new ApiResponse<>();

        try {

            Pageable pageable = PageRequest.of(page, size,
                    Sort.by("createdTime").descending());

            Page<Car> cars = carRepository.findAll(pageable);

            if (cars.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_CARS_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.CARS_FETCHED_SUCCESSFULLY);
            response.setData(cars);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }
}