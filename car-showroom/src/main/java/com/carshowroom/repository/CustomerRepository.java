package com.carshowroom.repository;

import com.carshowroom.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);

    // Search by first name or last name containing keyword
    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    // Get all non-deleted customers
    List<Customer> findByDeleted(Boolean deleted);

    // Check if email exists for a different customer (used in update)
    @Query("SELECT c FROM Customer c WHERE c.email = :email AND c.id != :customerId")
    Optional<Customer> findByEmailAndIdNot(@Param("email") String email,
                                           @Param("customerId") Long customerId);

}
