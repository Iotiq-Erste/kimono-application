package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.seller.SellerCreateRequest;
import com.iotiq.application.messages.seller.SellerCreateResponse;
import com.iotiq.application.messages.seller.SellerDto;
import com.iotiq.application.messages.seller.SellerUpdateRequest;
import com.iotiq.application.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(@SellerManagementAuth.CREATE)")
    public SellerCreateResponse createSeller(@RequestBody SellerCreateRequest request) {
        return sellerService.createSeller(request);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(@SellerManagementAuth.VIEW)")
    public SellerDto getSeller(){
        return ModelMapperUtil.map(sellerService.getCurrentSeller(), SellerDto.class);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority(@SellerManagementAuth.DELETE)")
    public void deleteSellerByCustomerId(){ sellerService.inactiveSeller();}

    @PutMapping
    @PreAuthorize("hasAuthority(@SellerManagementAuth.UPDATE)")
    public void updateSeller(@RequestBody SellerUpdateRequest request){
        sellerService.update(request);
    }

}
