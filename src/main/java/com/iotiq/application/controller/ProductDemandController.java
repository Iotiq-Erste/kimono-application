package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.productdemand.ProductDemandCreateResponse;
import com.iotiq.application.messages.productdemand.ProductDemandDetailResponse;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.messages.productdemand.ProductDemandResponse;
import com.iotiq.application.service.ProductDemandService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/product-demands")
public class ProductDemandController {

    private final ProductDemandService productDemandService;

    @PostMapping
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDemandCreateResponse createProductDemand(@RequestBody ProductDemandRequest productDemandRequest) {
        return new ProductDemandCreateResponse(productDemandService.createProductDemand(productDemandRequest).getId());
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public List<ProductDemandDetailResponse> getProductDemands() {
        return ModelMapperUtil.map(productDemandService.getProductDemands(), ProductDemandDetailResponse.class);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public List<ProductDemandResponse> getProductDemandsForCurrentCustomer() {
        return ModelMapperUtil.map(productDemandService.getProductDemandsForCurrentCustomer(), ProductDemandResponse.class);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public ProductDemandResponse getProductDemandForCurrentCustomer(@PathVariable UUID id) {
        return ModelMapperUtil.map(productDemandService.getProductDemandForCurrentCustomer(id), ProductDemandResponse.class);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.UPDATE)")
    public void updateProductDemand(@PathVariable("id") UUID id, @RequestBody ProductDemandRequest productDemandRequest) {
        productDemandService.updateProductDemand(id, productDemandRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.DELETE)")
    public void deleteProductDemand(@PathVariable UUID id) {
        productDemandService.deleteProductDemand(id);
    }
}
