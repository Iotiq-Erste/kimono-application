package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Cart;
import com.iotiq.application.messages.cart.CartCreateRequest;
import com.iotiq.application.messages.cart.CartCreateResponse;
import com.iotiq.application.messages.cart.CartDto;
import com.iotiq.application.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public CartCreateResponse createCart(CartCreateRequest request) {
       Cart cart = cartRepository.save(ModelMapperUtil.map(request, Cart.class));
       return new CartCreateResponse(cart.getId());
    }

    public CartDto getCartByOwner(UUID owner) {
        return ModelMapperUtil.map(cartRepository.findByOwner(owner), CartDto.class);
    }



}
