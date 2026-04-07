package com.carshowroom.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.carshowroom.constants.CarShowroomConstants;
import com.carshowroom.exception.InvalidCredentialsException;
import com.carshowroom.exception.UserNotFoundException;
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

        // Edge Case 1: Check passwords match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ApiResponse.<String>builder()
                    .status(CarShowroomConstants.STATUS_FAILURE)
                    .message(CarShowroomConstants.PASSWORD_MISMATCH)
                    .data(null)
                    .build();
        }

        // Edge Case 2: Check email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return ApiResponse.<String>builder()
                    .status(CarShowroomConstants.STATUS_FAILURE)
                    .message(CarShowroomConstants.INVALID_CREDENTIALS)
                    .data(null)
                    .build();
        }

        // Build and save user with encoded password
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);

        return ApiResponse.<String>builder()
                .status(CarShowroomConstants.STATUS_SUCCESS)
                .message(CarShowroomConstants.USER_REGISTERED_SUCCESSFULLY)
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<String> loginUser(LoginRequest request) {

        ApiResponse<String> response = new ApiResponse<>();

        try {

            // Edge Case 1: Check email exists
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new InvalidCredentialsException(
                            CarShowroomConstants.INVALID_CREDENTIALS));

            // Edge Case 2: Check account is enabled
            if (!user.isEnabled()) {
                response.setStatus(CarShowroomConstants.STATUS_FAILURE);
                response.setMessage("Account is disabled");
                response.setData(null);
                return response;
            }

            // Edge Case 3: Check password matches
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException(
                        CarShowroomConstants.INVALID_CREDENTIALS);
            }

            // Login success — JWT will be added on Day 16
            response.setStatus(CarShowroomConstants.STATUS_SUCCESS);
            response.setMessage(CarShowroomConstants.LOGIN_SUCCESS);
            response.setData(null);

        } catch (InvalidCredentialsException e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);

        } catch (Exception e) {

            response.setStatus(CarShowroomConstants.STATUS_FAILURE);
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ApiResponse<User> getUserById(Long userId) {

        // Fetch user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        CarShowroomConstants.USER_NOT_FOUND + userId));

        // Return response
        return new ApiResponse<>(
                CarShowroomConstants.STATUS_SUCCESS,
                CarShowroomConstants.USER_FETCHED_SUCCESSFULLY,
                user
        );
    }

}
