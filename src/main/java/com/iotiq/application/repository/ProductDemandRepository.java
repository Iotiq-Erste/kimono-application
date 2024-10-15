package com.iotiq.application.repository;

import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductDemandRepository extends JpaRepository<ProductDemand, UUID> {

    Page<ProductDemand> findAllByCustomerAndIsActiveTrue(Customer customer, Pageable pageable);
    Optional<ProductDemand> findByIdAndCustomerAndIsActiveTrue(UUID id, Customer customer);
    Page<ProductDemand> findAllByIsActiveTrueAndSellerIsNull(Pageable pageable);
    Optional<ProductDemand> findByIdAndSellerIsNull(UUID id);
}
