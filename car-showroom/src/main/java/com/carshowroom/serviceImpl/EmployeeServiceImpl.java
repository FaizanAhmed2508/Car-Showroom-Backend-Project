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
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<Employee> getEmployeeById(Long employeeId) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<List<Employee>> getAllEmployees() {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> updateEmployee(Long employeeId, EmployeeRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> deleteEmployee(Long employeeId) {
        // TODO: implement
        return null;
    }

}
