package com.carshowroom.repository;

import com.carshowroom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // Get all users by role
    List<User> findByRole(String role);

    // Check if email already exists
    boolean existsByEmail(String email);

}