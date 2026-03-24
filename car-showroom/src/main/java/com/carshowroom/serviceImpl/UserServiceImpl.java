package com.carshowroom.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.model.User;
import com.carshowroom.repository.UserRepository;
import com.carshowroom.request.LoginRequest;
import com.carshowroom.request.UserRegistrationRequest;
import com.carshowroom.response.ApiResponse;
import com.carshowroom.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<String> registerUser(UserRegistrationRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public ApiResponse<String> loginUser(LoginRequest request) {
        // TODO: implement
        return null;
    }

}
