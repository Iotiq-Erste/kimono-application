package com.iotiq.application.repository;

import com.iotiq.application.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.brand IS NOT NULL")
    List<String> findDistinctBrands();
}
