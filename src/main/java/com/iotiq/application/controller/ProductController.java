package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Product;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductResponse;
import com.iotiq.application.service.ProductService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public PagedResponse<ProductResponse> getAll(ProductFilter filter, Sort sort) {

        Page<Product> page = productService.getAll(filter, sort);
        List<ProductResponse> responseList = ModelMapperUtil.map(page.getContent(), ProductResponse.class);

        return PagedResponseBuilder.createResponse(page, responseList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public ProductResponse getOne(@PathVariable UUID id) {
        Product product = productService.getOne(id);
        return ModelMapperUtil.map(product, ProductResponse.class);
    }
}
