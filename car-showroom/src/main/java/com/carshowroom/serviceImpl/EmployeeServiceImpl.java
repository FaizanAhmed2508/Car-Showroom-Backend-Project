package com.carshowroom.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.exception.EmployeeNotFoundException;
import com.carshowroom.model.Employee;
import com.carshowroom.repository.EmployeeRepository;
import com.carshowroom.request.EmployeeRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ApiResponse<String> addEmployee(EmployeeRequest request) {

        // Edge Case 1: Check email already exists
        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            return ApiResponse.<String>builder()
                    .status(CarShowroomConstants.STATUS_FAILURE)
                    .message(CarShowroomConstants.EMPLOYEE_EMAIL_EXISTS)
                    .data(null)
                    .build();
        }

        // Build and save employee
        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .employeeRole(request.getEmployeeRole())
                .isActive(CarShowroomConstants.ACTIVE)
                .build();

        employeeRepository.save(employee);

        return ApiResponse.<String>builder()
                .status(CarShowroomConstants.STATUS_SUCCESS)
                .message(CarShowroomConstants.EMPLOYEE_ADDED_SUCCESSFULLY)
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<Employee> getEmployeeById(Long employeeId) {

        // Fetch employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        CarShowroomConstants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

        // Return response
        return new ApiResponse<>(
                CarShowroomConstants.STATUS_SUCCESS,
                CarShowroomConstants.EMPLOYEE_FETCHED_SUCCESSFULLY,
                employee
        );
    }

    @Override
    public ApiResponse<List<Employee>> getAllEmployees() {

        ApiResponse<List<Employee>> response = new ApiResponse<>();

        try {

            // Only fetch active employees
            List<Employee> employees = employeeRepository.findByIsActive(
                    CarShowroomConstants.ACTIVE);

            if (employees.isEmpty()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage(CarShowroomConstants.NO_EMPLOYEES_FOUND);
                response.setData(null);
                return response;
            }

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.EMPLOYEES_FETCHED_SUCCESSFULLY);
            response.setData(employees);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> updateEmployee(Long employeeId, EmployeeRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            CarShowroomConstants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

            // Check if new email belongs to a different employee
            if (employeeRepository.findByEmailAndIdNot(
                    request.getEmail(), employeeId).isPresent()) {
                throw new RuntimeException(CarShowroomConstants.EMPLOYEE_EMAIL_EXISTS);
            }

            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setEmail(request.getEmail());
            employee.setPhone(request.getPhone());
            employee.setEmployeeRole(request.getEmployeeRole());

            employeeRepository.save(employee);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.EMPLOYEE_UPDATED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<String> deleteEmployee(Long employeeId) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            CarShowroomConstants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

            // Soft delete — mark as inactive
            employee.setIsActive(CarShowroomConstants.IN_ACTIVE);
            employeeRepository.save(employee);

            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.EMPLOYEE_DELETED_SUCCESSFULLY);
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public List<Employee> searchEmployeeByName(String name) {

        if (name == null) {
            return List.of();
        }

        System.out.println(employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name));
        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

}
