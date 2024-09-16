package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Cart;
import com.iotiq.application.messages.cart.CartCreateRequest;
import com.iotiq.application.messages.cart.CartCreateResponse;
import com.iotiq.application.messages.cart.CartUpdateRequest;
import com.iotiq.application.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;

    @Transactional
    public CartCreateResponse createCart(CartCreateRequest request) {
       Cart cart = cartRepository.save(ModelMapperUtil.map(request, Cart.class));
       return new CartCreateResponse(cart.getId());
    }

    public Cart getCart() {
        return customerService.getCurrentCustomer().getCart();
    }

    @Transactional
    public void update(CartUpdateRequest request) {
        //todo rearrange exception

        Cart cart = getCart();
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(ModelMapperUtil.map(request, Cart.class));
    }

    public void delete() {
        cartRepository.delete(customerService.getCurrentCustomer().getCart());
    }
}
