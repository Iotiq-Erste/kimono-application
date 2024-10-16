package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.messages.orderedproduct.OrderedProductResponse;
import com.iotiq.application.service.OrderedProductService;
import com.iotiq.application.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ordered-products")
public class OrderedProductController {

    private final OrderedProductService orderedProductService;
    private final SellerService sellerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@OrderedProductManagementAuth.VIEW)")
    public List<OrderedProductResponse> getOrderedProducts() {
        List<OrderedProductDto> orderedProduct = orderedProductService.getOrderedProducts(sellerService.getCurrentSellerOrCreate());
        return ModelMapperUtil.map(orderedProduct, OrderedProductResponse.class);
    }
}
