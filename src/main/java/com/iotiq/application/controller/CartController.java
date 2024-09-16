package com.iotiq.application.controller;

import com.iotiq.application.domain.Cart;
import com.iotiq.application.messages.cart.CartCreateRequest;
import com.iotiq.application.messages.cart.CartCreateResponse;
import com.iotiq.application.messages.cart.CartUpdateRequest;
import com.iotiq.application.service.CartService;
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
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority(@CartManagementAuth.CREATE)")
    public CartCreateResponse createCart(@RequestBody CartCreateRequest request) {
        return cartService.createCart(request);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.VIEW)")
    public Cart getCart(){
        return cartService.getCart();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.DELETE)")
    public void deleteCart(){ cartService.delete();}

    @PutMapping
    @PreAuthorize("hasAuthority(@CartManagementAuth.UPDATE)")
    public void updateCart(@RequestBody CartUpdateRequest request){
        cartService.update(request);
    }

}
