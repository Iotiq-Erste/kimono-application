package com.iotiq.application.controller;

import com.iotiq.application.entity.product.Product;
import com.iotiq.application.messages.product.*;
import com.iotiq.application.service.ProductService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public PagedResponse<ProductDto> getAll(ProductFilter filter, Sort sort) {
        Page<Product> page = productService.getAll(filter, sort);
        List<ProductDto> dtos = page.getContent().stream().map(ProductDto::of).toList();

        return PagedResponseBuilder.createResponse(page, dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public ProductDto getOne(@PathVariable UUID id) {
        Product product = productService.getOne(id);
        return ProductDto.of(product);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(@RequestBody @Valid ProductCreateRequest request){
        return productService.createProduct(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid ProductUpdateRequest request) {
        productService.update(id, request);
    }
}
