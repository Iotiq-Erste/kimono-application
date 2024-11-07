package com.iotiq.application.repository;


import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByIdAndCustomer(UUID id, Customer customer);
    Page<Order> findAllByCustomerAndIsVisibleTrue(Customer customer, Pageable pageable);
}
