package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.cart.CartResponse;
import com.iotiq.application.messages.cart.CartUpdateRequest;
import com.iotiq.application.service.CartService;
import com.iotiq.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.VIEW)")
    public CartResponse getCart(){
        return ModelMapperUtil.map(cartService.getCart(customerService.getCurrentCustomerOrCreate()), CartResponse.class);
    }

    @PutMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.UPDATE)")
    public void updateCart(@RequestBody CartUpdateRequest request){
        cartService.update(request, customerService.getCurrentCustomerOrCreate());
    }

}
