package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.productdemand.ProductDemandCreateResponse;
import com.iotiq.application.messages.productdemand.ProductDemandDto;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.messages.productdemand.ProductDemandResponse;
import com.iotiq.application.messages.productdemand.ProductDemandUpdateRequest;
import com.iotiq.application.service.CustomerService;
import com.iotiq.application.service.ProductDemandService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDemandCreateResponse createProductDemand(@RequestBody ProductDemandRequest productDemandRequest) {
        return new ProductDemandCreateResponse(productDemandService.createProductDemand(productDemandRequest, customerService.getCurrentCustomerOrCreate()).getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW) and hasRole('ROLE_CUSTOMER')")
    public PagedResponse<ProductDemandResponse> getProductDemandsForCustomer(Pageable pageable) {
        Page<ProductDemandDto> page = productDemandService.getProductDemandsForCurrentCustomer(pageable, customerService.getCurrentCustomerOrCreate());
        List<ProductDemandResponse> productDemandResponses = ModelMapperUtil.map(page.getContent(), ProductDemandResponse.class);

        return PagedResponseBuilder.createResponse(page, productDemandResponses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.VIEW) and hasRole('ROLE_CUSTOMER')")
    public ProductDemandResponse getProductDemand(@PathVariable UUID id) {
        return ModelMapperUtil.map(productDemandService.getProductDemandOfCustomerByID(id, customerService.getCurrentCustomerOrCreate()), ProductDemandResponse.class);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.UPDATE) and hasRole('ROLE_CUSTOMER')")
    public void updateProductDemand(@PathVariable("id") UUID id, @RequestBody ProductDemandUpdateRequest updateRequest) {
        productDemandService.updateProductDemand(id, updateRequest, customerService.getCurrentCustomerOrCreate());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductDemandManagementAuth.DELETE)")
    public void deleteProductDemand(@PathVariable UUID id) {
        productDemandService.deleteProductDemand(id, customerService.getCurrentCustomerOrCreate());
    }
}
