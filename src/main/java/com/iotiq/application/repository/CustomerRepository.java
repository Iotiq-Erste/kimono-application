package com.iotiq.application.repository;

import com.iotiq.application.domain.Customer;
import com.iotiq.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByUser(User user);
    boolean existsByUser(User user);
}
