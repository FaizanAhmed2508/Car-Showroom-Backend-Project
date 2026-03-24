package com.carshowroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.carshowroom.request.LoginRequest;
import com.carshowroom.request.UserRegistrationRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Register user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        // TODO: implement
        return null;
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginRequest request) {
        // TODO: implement
        return null;
    }

}
