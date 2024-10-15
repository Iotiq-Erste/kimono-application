package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.productdemand.ProductDemandDto;
import com.iotiq.application.messages.productdemand.ProductDemandResponse;
import com.iotiq.application.messages.productdemand.SellerProductDemandUpdateRequest;
import com.iotiq.application.service.ProductDemandService;
import com.iotiq.application.service.SellerService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/api/v1/seller/product-demands")
public class SellerProductDemandController {

    private final ProductDemandService productDemandService;
    private final SellerService sellerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public PagedResponse<ProductDemandResponse> getProductDemands(Sort sort) {
        Page<ProductDemandDto> page = productDemandService.getProductDemandsForSeller(sort);
        List<ProductDemandResponse> productDemandResponses = ModelMapperUtil.map(page.getContent(), ProductDemandResponse.class);

        return PagedResponseBuilder.createResponse(page, productDemandResponses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW)")
    public ProductDemandResponse getProductDemand(@PathVariable UUID id) {
        return ModelMapperUtil.map(productDemandService.getProductDemand(id), ProductDemandResponse.class);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.UPDATE)")
    public void updateDemandStatus(@PathVariable("id") UUID id, @RequestBody SellerProductDemandUpdateRequest updateRequest) {
        productDemandService.updateDemandStatusBySeller(id, updateRequest, sellerService.getCurrentSellerOrCreate());
    }
}
