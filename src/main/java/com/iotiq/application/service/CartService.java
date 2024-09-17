package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Cart;
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

}
