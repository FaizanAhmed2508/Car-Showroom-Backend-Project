package com.carshowroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.model.User;
import com.carshowroom.request.LoginRequest;
import com.carshowroom.request.UserRegistrationRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carshowroom.utility.CarShowroomUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        // Edge case: request is null
        if (request == null) {
            ApiResponse<String> response = new ApiResponse<>();
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.REQUEST_BODY_IS_MISSING_OR_CONTAINS_INVALID_DATA);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        ApiResponse<String> response = userService.registerUser(request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(
            @Valid @RequestBody LoginRequest request) {

        // Edge case: request is null
        if (request == null) {
            ApiResponse<String> response = new ApiResponse<>();
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.REQUEST_BODY_IS_MISSING_OR_CONTAINS_INVALID_DATA);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        ApiResponse<String> response = userService.loginUser(request);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long userId) {

        ApiResponse<User> response = new ApiResponse<>();

        // Edge case: invalid ID
        if (CarShowroomUtil.isInvalidId(userId)) {
            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(CarShowroomConstants.INVALID_USER_ID);
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response = userService.getUserById(userId);

        if (CarShowroomConstants.STATUS_SUCCESS.equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}