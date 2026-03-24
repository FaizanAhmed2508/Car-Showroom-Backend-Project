package com.carshowroom.service;

import com.carshowroom.model.User;
import com.carshowroom.request.LoginRequest;
import com.carshowroom.request.UserRegistrationRequest;
import com.carshowroom.response.ApiResponse;

public interface UserService {

    ApiResponse<String> registerUser(UserRegistrationRequest request);
    ApiResponse<String> loginUser(LoginRequest request);
    // Add more methods as needed

}
