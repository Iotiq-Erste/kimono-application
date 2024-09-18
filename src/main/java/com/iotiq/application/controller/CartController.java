package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.cart.CartDto;
import com.iotiq.application.messages.cart.CartUpdateRequest;
import com.iotiq.application.service.CartService;
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

    @GetMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.VIEW)")
    public CartDto getCart(){
        return ModelMapperUtil.map(cartService.getCart(), CartDto.class);
    }

    @PutMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.UPDATE)")
    public void updateCart(@RequestBody CartUpdateRequest request){
        cartService.update(request);
    }

}
