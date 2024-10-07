package com.iotiq.application.repository;


import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByOrderedProductsSellerId(UUID sellerId);
    Optional<Order> findByIdAndCustomer(UUID id, Customer customer);
}
