package com.iotiq.application.repository;

import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDemandRepository extends JpaRepository<ProductDemand, UUID> {

    Optional<List<ProductDemand>> findAllByCustomerAndIsActiveTrue(Customer customer);
    Optional<ProductDemand> findByIdAndCustomerAndIsActiveTrue(UUID id, Customer customer);
    List<ProductDemand> findAllByIsActiveTrue();
}
