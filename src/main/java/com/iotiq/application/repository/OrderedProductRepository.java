package com.iotiq.application.repository;

import com.iotiq.application.domain.OrderedProduct;
import com.iotiq.application.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, UUID> {
    List<OrderedProduct> findAllBySeller(Seller seller);
}
