package com.iotiq.application.controller;

import com.iotiq.application.messages.cart.CartCreateRequest;
import com.iotiq.application.messages.cart.CartCreateResponse;
import com.iotiq.application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartCreateResponse createCart(@RequestBody CartCreateRequest request) {
        return cartService.createCart(request);
    }

}
