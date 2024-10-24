package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.seller.SellerResponse;
import com.iotiq.application.messages.seller.SellerUpdateRequest;
import com.iotiq.application.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller")
public class SellerController {

    private final SellerService sellerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@SellerManagementAuth.VIEW)")
    public SellerResponse getSeller(){
        return ModelMapperUtil.map(sellerService.getSeller(), SellerResponse.class);
    }

    @PutMapping
    @PreAuthorize("hasAuthority(@SellerManagementAuth.UPDATE)")
    public void updateSeller(@RequestBody SellerUpdateRequest request){
        sellerService.update(request);
    }

}
