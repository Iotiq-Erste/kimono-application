package com.iotiq.application.repository;

import com.iotiq.application.domain.Product;
import com.iotiq.application.messages.brand.BrandProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    List<BrandProjection> findDistinctByBrandIsNotNull();
}
