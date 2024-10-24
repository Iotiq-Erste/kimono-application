package com.iotiq.application.repository;

import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import com.iotiq.application.domain.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ProductDemandRepository extends JpaRepository<ProductDemand, UUID> {

    Page<ProductDemand> findAllByCustomerAndIsActiveTrue(Customer customer, Pageable pageable);
    Optional<ProductDemand> findByIdAndCustomerAndIsActiveTrue(UUID id, Customer customer);
    Page<ProductDemand> findAllByIsActiveTrueAndSellerIsNull(Pageable pageable);
    Page<ProductDemand> findAllByIsActiveTrueAndSeller(Pageable pageable, Seller seller);

    @Query("SELECT pd FROM ProductDemand pd WHERE pd.id = :id AND (pd.seller IS NULL OR pd.seller = :seller)")
    Optional<ProductDemand> findByIdAndSellerIsNullOrSeller(UUID id, Seller seller);
}
