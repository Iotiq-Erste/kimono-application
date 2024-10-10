package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.productdemand.ProductDemandCreateResponse;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.messages.productdemand.ProductDemandResponse;
import com.iotiq.application.messages.productdemand.ProductDemandUpdateRequest;
import com.iotiq.application.service.ProductDemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
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

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public List<ProductDemandResponse> getProductDemands(Authentication authentication) throws RoleNotFoundException {
        return ModelMapperUtil.map(productDemandService.getProductDemands(authentication), ProductDemandResponse.class);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public ProductDemandResponse getProductDemand(@PathVariable UUID id) {
        return ModelMapperUtil.map(productDemandService.getProductDemandByID(id), ProductDemandResponse.class);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.UPDATE)")
    public void updateProductDemand(@PathVariable("id") UUID id, @RequestBody ProductDemandUpdateRequest updateRequest) {
        productDemandService.updateProductDemand(id, updateRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.DELETE)")
    public void deleteProductDemand(@PathVariable UUID id) {
        productDemandService.deleteProductDemand(id);
    }
}
