package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.messages.orderedproduct.OrderedProductResponse;
import com.iotiq.application.service.OrderedProductService;
import com.iotiq.application.service.SellerService;
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PagedResponse<OrderedProductResponse> getOrderedProducts(PageableRequest pageableRequest, Sort sort) {
        Page<OrderedProductDto> page = orderedProductService.getOrderedProducts(pageableRequest, sort, sellerService.getCurrentSeller());
        List<OrderedProductResponse> responseList = ModelMapperUtil.map(page.getContent(), OrderedProductResponse.class);

        return PagedResponseBuilder.createResponse(page, responseList);
    }
}
