package com.iotiq.application.controller;

import com.iotiq.application.entity.product.Product;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductCreateResponse;
import com.iotiq.application.messages.product.ProductDto;
import com.iotiq.application.messages.product.ProductFilterRequest;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.service.ProductService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
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
    private final LoggersEndpoint loggersEndpoint;

    @PostMapping("/filteredProducts")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public PagedResponse<ProductDto> getAll(@RequestBody ProductFilterRequest filter, Sort sort) {
        System.out.println("ProductController.getAll");
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
