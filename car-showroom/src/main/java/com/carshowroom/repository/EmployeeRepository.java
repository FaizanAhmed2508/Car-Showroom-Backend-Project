package com.carshowroom.repository;

import com.carshowroom.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByPhone(String phone);

    // Search by first name or last name
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    // Get all active employees
    List<Employee> findByIsActive(String isActive);

    // Check if email exists for a different employee (used in update)
    @Query("SELECT e FROM Employee e WHERE e.email = :email AND e.id != :employeeId")
    Optional<Employee> findByEmailAndIdNot(@Param("email") String email,
                                           @Param("employeeId") Long employeeId);

}