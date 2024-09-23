package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Product;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductCreateResponse;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductResponse;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.service.ProductService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(@RequestBody @Valid ProductCreateRequest request) {
        return new ProductCreateResponse(productService.createProduct(request).getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.DELETE)")
    public void delete(@PathVariable("id") UUID id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.UPDATE)")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid ProductUpdateRequest request) {
        productService.update(id, request);
    }
}
